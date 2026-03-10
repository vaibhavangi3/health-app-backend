package com.healthapp.precomputed.controller;

import com.healthapp.common.response.ApiResponse;
import com.healthapp.precomputed.dto.MealAnalyzeResponse;
import com.healthapp.precomputed.service.MealAnalysisService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/meal")
public class MealAnalysisController {
  private final MealAnalysisService mealAnalysisService;

  public MealAnalysisController(MealAnalysisService mealAnalysisService) {
    this.mealAnalysisService = mealAnalysisService;
  }

  @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<MealAnalyzeResponse>> analyzeMeal(
      @RequestParam("image") MultipartFile image) {
    MealAnalyzeResponse data = mealAnalysisService.analyzeMeal(image);
    return ResponseEntity.ok(ApiResponse.ok("analyzed", data));
  }
}
