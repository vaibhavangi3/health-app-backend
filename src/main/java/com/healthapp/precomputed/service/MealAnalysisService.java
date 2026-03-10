package com.healthapp.precomputed.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthapp.precomputed.dto.MealAnalyzeResponse;
import com.healthapp.precomputed.entity.MealNutritionLog;
import com.healthapp.precomputed.repository.MealNutritionLogRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MealAnalysisService {
  private static final String GEMINI_API =
      "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s";

  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;
  private final MealNutritionLogRepository mealNutritionLogRepository;
  private final String geminiApiKey;
  private final String geminiModel;

  public MealAnalysisService(
      ObjectMapper objectMapper,
      MealNutritionLogRepository mealNutritionLogRepository,
      @Value("${gemini.api-key:}") String geminiApiKey,
      @Value("${gemini.model:gemini-2.5-flash}") String geminiModel) {
    this.objectMapper = objectMapper;
    this.mealNutritionLogRepository = mealNutritionLogRepository;
    this.geminiApiKey = geminiApiKey;
    this.geminiModel = geminiModel;
    this.restTemplate = new RestTemplate();
  }

  public MealAnalyzeResponse analyzeMeal(MultipartFile image) {
    validateImage(image);

    String modelOutput = callGemini(image);
    JsonNode parsed = parseGeminiJson(modelOutput);

    List<String> labels = extractLabels(parsed);
    String matchedFood =
        firstNonBlank(
            textField(parsed, "matchedFood"),
            textField(parsed, "food"),
            textField(parsed, "food_name"));

    if ((matchedFood == null || matchedFood.isBlank()) && !labels.isEmpty()) {
      matchedFood = labels.get(0);
    }

    if (matchedFood == null || matchedFood.isBlank()) {
      matchedFood = "Unknown meal";
    }

    Integer carbs = firstInt(parsed, "carbs", "carbs_g", "carbohydrates", "carbohydrates_g");
    Integer protein = firstInt(parsed, "protein", "protein_g", "proteins");
    Integer fats = firstInt(parsed, "fats", "fat", "fats_g", "fat_g", "lipids");
    Double confidence = firstDouble(parsed, "confidence", "score");

    MealNutritionLog log = new MealNutritionLog();
    log.setFoodName(matchedFood);
    log.setDetectedLabels(asJson(labels));
    log.setCarbs(carbs);
    log.setProtein(protein);
    log.setFats(fats);
    log.setConfidence(confidence);
    log.setModelName(geminiModel);
    log.setRawResponse(asJson(parsed));
    mealNutritionLogRepository.save(log);

    return new MealAnalyzeResponse(labels, matchedFood, carbs, protein, fats, confidence);
  }

  private void validateImage(MultipartFile image) {
    if (image == null || image.isEmpty()) {
      throw new IllegalArgumentException("image file is required");
    }

    String contentType = image.getContentType();
    if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
      throw new IllegalArgumentException("Only image uploads are supported");
    }

    if (geminiApiKey == null || geminiApiKey.isBlank()) {
      throw new IllegalArgumentException("Gemini API key is not configured");
    }
  }

  private String callGemini(MultipartFile image) {
    String mimeType = image.getContentType() == null ? "image/jpeg" : image.getContentType();
    String base64;
    try {
      base64 = Base64.getEncoder().encodeToString(image.getBytes());
    } catch (IOException ex) {
      throw new IllegalArgumentException("Unable to read uploaded image");
    }

    String prompt =
        "Analyze this meal image and estimate nutrition. "
            + "Return ONLY valid JSON with this exact schema: "
            + "{\"matchedFood\":string,\"detectedLabels\":string[],\"carbs\":number,\"protein\":number,\"fats\":number,\"confidence\":number}. "
            + "Use grams for carbs/protein/fats. If unsure, provide best estimate.";

    Map<String, Object> payload =
        Map.of(
            "contents",
            List.of(
                Map.of(
                    "parts",
                    List.of(
                        Map.of("text", prompt),
                        Map.of("inline_data", Map.of("mime_type", mimeType, "data", base64))))),
            "generationConfig",
            Map.of("temperature", 0.2, "responseMimeType", "application/json"));

    String url = String.format(GEMINI_API, geminiModel, geminiApiKey);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    try {
      String requestBody = objectMapper.writeValueAsString(payload);
      ResponseEntity<String> response =
          restTemplate.postForEntity(url, new HttpEntity<>(requestBody, headers), String.class);

      if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
        throw new IllegalArgumentException(
            "Gemini request failed (HTTP " + response.getStatusCode().value() + ")");
      }

      JsonNode root = objectMapper.readTree(response.getBody());
      JsonNode textNode =
          root.path("candidates").path(0).path("content").path("parts").path(0).path("text");

      String text = textNode.asText("").trim();
      if (text.isEmpty()) {
        throw new IllegalArgumentException("Gemini returned empty analysis");
      }

      return text;
    } catch (HttpStatusCodeException ex) {
      throw new IllegalArgumentException(extractGeminiError(ex.getResponseBodyAsString()));
    } catch (ResourceAccessException ex) {
      throw new IllegalArgumentException("Unable to reach Gemini API");
    } catch (IOException ex) {
      throw new IllegalArgumentException("Unable to parse Gemini response");
    }
  }

  private JsonNode parseGeminiJson(String raw) {
    String cleaned = raw.trim();
    if (cleaned.startsWith("```")) {
      cleaned = cleaned.replaceAll("^```json\\s*", "");
      cleaned = cleaned.replaceAll("^```\\s*", "");
      cleaned = cleaned.replaceAll("\\s*```$", "");
    }

    try {
      return objectMapper.readTree(cleaned);
    } catch (IOException ex) {
      throw new IllegalArgumentException("Gemini returned invalid JSON for nutrition output");
    }
  }

  private List<String> extractLabels(JsonNode node) {
    List<String> labels = new ArrayList<>();

    JsonNode detected = node.get("detectedLabels");
    if (detected != null && detected.isArray()) {
      for (JsonNode item : detected) {
        String value = item.asText("").trim();
        if (!value.isEmpty()) {
          labels.add(value);
        }
      }
    }

    if (labels.isEmpty()) {
      JsonNode labelsNode = node.get("labels");
      if (labelsNode != null && labelsNode.isArray()) {
        for (JsonNode item : labelsNode) {
          String value = item.asText("").trim();
          if (!value.isEmpty()) {
            labels.add(value);
          }
        }
      }
    }

    return labels;
  }

  private String textField(JsonNode node, String field) {
    JsonNode value = node.get(field);
    if (value == null || value.isNull()) {
      return null;
    }
    String text = value.asText("").trim();
    return text.isEmpty() ? null : text;
  }

  private Integer firstInt(JsonNode node, String... fields) {
    for (String field : fields) {
      JsonNode value = node.get(field);
      Integer parsed = asInt(value);
      if (parsed != null) {
        return parsed;
      }
    }
    return null;
  }

  private Double firstDouble(JsonNode node, String... fields) {
    for (String field : fields) {
      JsonNode value = node.get(field);
      Double parsed = asDouble(value);
      if (parsed != null) {
        return parsed;
      }
    }
    return null;
  }

  private Integer asInt(JsonNode node) {
    if (node == null || node.isNull()) {
      return null;
    }

    if (node.isNumber()) {
      return (int) Math.round(node.asDouble());
    }

    if (node.isTextual()) {
      String text = node.asText("").trim().toLowerCase(Locale.ROOT).replace("g", "");
      if (text.isEmpty()) {
        return null;
      }
      try {
        return (int) Math.round(Double.parseDouble(text));
      } catch (NumberFormatException ignored) {
        return null;
      }
    }

    return null;
  }

  private Double asDouble(JsonNode node) {
    if (node == null || node.isNull()) {
      return null;
    }

    if (node.isNumber()) {
      return node.asDouble();
    }

    if (node.isTextual()) {
      String text = node.asText("").trim();
      if (text.isEmpty()) {
        return null;
      }
      try {
        return Double.parseDouble(text);
      } catch (NumberFormatException ignored) {
        return null;
      }
    }

    return null;
  }

  private String extractGeminiError(String responseBody) {
    if (responseBody == null || responseBody.isBlank()) {
      return "Gemini request failed";
    }

    try {
      JsonNode root = objectMapper.readTree(responseBody);
      String message = root.path("error").path("message").asText("").trim();
      if (!message.isEmpty()) {
        return message;
      }
    } catch (IOException ignored) {
      // Fallback below.
    }

    return responseBody.trim();
  }

  private String asJson(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException ex) {
      return "[]";
    }
  }

  private String firstNonBlank(String... values) {
    for (String value : values) {
      if (value != null && !value.isBlank()) {
        return value;
      }
    }
    return null;
  }
}
