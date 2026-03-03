package com.healthapp.precomputed.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FoodCreateRequest {
  @NotBlank
  private String foodName;

  @NotNull
  @Min(0)
  private Integer carbs;

  @NotNull
  @Min(0)
  private Integer protein;

  @NotNull
  @Min(0)
  private Integer fats;

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
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
}
