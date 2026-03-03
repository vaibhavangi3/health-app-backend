package com.healthapp.precomputed.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExerciseCreateRequest {
  @NotBlank
  private String name;

  @NotBlank
  private String category;

  @NotBlank
  private String muscleGroup;

  @NotNull
  @Min(0)
  private Integer calorieBurnPerSet;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getMuscleGroup() {
    return muscleGroup;
  }

  public void setMuscleGroup(String muscleGroup) {
    this.muscleGroup = muscleGroup;
  }

  public Integer getCalorieBurnPerSet() {
    return calorieBurnPerSet;
  }

  public void setCalorieBurnPerSet(Integer calorieBurnPerSet) {
    this.calorieBurnPerSet = calorieBurnPerSet;
  }
}
