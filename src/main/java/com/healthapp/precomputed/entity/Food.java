package com.healthapp.precomputed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "precomputed_foods")
public class Food {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long foodId;

  @Column(nullable = false)
  private String foodName;

  @Column(nullable = false)
  private Integer carbs;

  @Column(nullable = false)
  private Integer protein;

  @Column(nullable = false)
  private Integer fats;

  @Column(name = "created_by", nullable = false)
  private Long createdBy;

  public Long getFoodId() {
    return foodId;
  }

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

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }
}
