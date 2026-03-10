package com.healthapp.precomputed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "meal_nutrition_logs")
public class MealNutritionLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "food_name", nullable = false)
  private String foodName;

  @Column(name = "detected_labels", columnDefinition = "TEXT")
  private String detectedLabels;

  @Column(name = "carbs")
  private Integer carbs;

  @Column(name = "protein")
  private Integer protein;

  @Column(name = "fats")
  private Integer fats;

  @Column(name = "confidence")
  private Double confidence;

  @Column(name = "model_name")
  private String modelName;

  @Column(name = "raw_response", columnDefinition = "TEXT")
  private String rawResponse;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @PrePersist
  void onCreate() {
    if (createdAt == null) {
      createdAt = Instant.now();
    }
  }

  public Long getId() {
    return id;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public String getDetectedLabels() {
    return detectedLabels;
  }

  public void setDetectedLabels(String detectedLabels) {
    this.detectedLabels = detectedLabels;
  }

  public Integer getCarbs() {
    return carbs;
  }

  public void setCarbs(Integer carbs) {
    this.carbs = carbs;
  }

  public Integer getProtein() {
    return protein;
  }

  public void setProtein(Integer protein) {
    this.protein = protein;
  }

  public Integer getFats() {
    return fats;
  }

  public void setFats(Integer fats) {
    this.fats = fats;
  }

  public Double getConfidence() {
    return confidence;
  }

  public void setConfidence(Double confidence) {
    this.confidence = confidence;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getRawResponse() {
    return rawResponse;
  }

  public void setRawResponse(String rawResponse) {
    this.rawResponse = rawResponse;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
