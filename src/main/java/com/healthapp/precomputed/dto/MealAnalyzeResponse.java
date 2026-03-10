package com.healthapp.precomputed.dto;

import java.util.List;

public class MealAnalyzeResponse {
  private final List<String> detectedLabels;
  private final String matchedFood;
  private final Integer carbs;
  private final Integer protein;
  private final Integer fats;
  private final Double confidence;

  public MealAnalyzeResponse(
      List<String> detectedLabels,
      String matchedFood,
      Integer carbs,
      Integer protein,
      Integer fats,
      Double confidence) {
    this.detectedLabels = detectedLabels;
    this.matchedFood = matchedFood;
    this.carbs = carbs;
    this.protein = protein;
    this.fats = fats;
    this.confidence = confidence;
  }

  public List<String> getDetectedLabels() {
    return detectedLabels;
  }

  public String getMatchedFood() {
    return matchedFood;
  }

  public Integer getCarbs() {
    return carbs;
  }

  public Integer getProtein() {
    return protein;
  }

  public Integer getFats() {
    return fats;
  }

  public Double getConfidence() {
    return confidence;
  }
}
