package com.healthapp.precomputed.controller;

import com.healthapp.common.response.ApiResponse;
import com.healthapp.precomputed.dto.ExerciseCreateRequest;
import com.healthapp.precomputed.dto.FoodCreateRequest;
import com.healthapp.precomputed.entity.Exercise;
import com.healthapp.precomputed.entity.Food;
import com.healthapp.precomputed.service.PrecomputedService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preData/{userId}")
public class PrecomputedController {
  private final PrecomputedService precomputedService;

  public PrecomputedController(PrecomputedService precomputedService) {
    this.precomputedService = precomputedService;
  }

  @GetMapping("/Exercise")
  public ResponseEntity<ApiResponse<List<Exercise>>> listExercises(@PathVariable Long userId) {
    return ResponseEntity.ok(ApiResponse.ok(precomputedService.listExercises()));
  }

  @GetMapping("/Food")
  public ResponseEntity<ApiResponse<List<Food>>> listFoods(@PathVariable Long userId) {
    return ResponseEntity.ok(ApiResponse.ok(precomputedService.listFoods()));
  }

  @PostMapping("/Exercise")
  public ResponseEntity<ApiResponse<Exercise>> createExercise(
      @PathVariable Long userId, @Valid @RequestBody ExerciseCreateRequest request) {
    Exercise created = precomputedService.createExercise(userId, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("created", created));
  }

  @PostMapping("/Food")
  public ResponseEntity<ApiResponse<Food>> createFood(
      @PathVariable Long userId, @Valid @RequestBody FoodCreateRequest request) {
    Food created = precomputedService.createFood(userId, request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("created", created));
  }
}
