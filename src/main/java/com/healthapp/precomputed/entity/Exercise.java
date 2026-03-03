package com.healthapp.precomputed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "precomputed_exercises")
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long exerciseId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private String muscleGroup;

  @Column(nullable = false)
  private Integer calorieBurnPerSet;

  @Column(name = "created_by", nullable = false)
  private Long createdBy;

  public Long getExerciseId() {
    return exerciseId;
  }

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

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }
}
