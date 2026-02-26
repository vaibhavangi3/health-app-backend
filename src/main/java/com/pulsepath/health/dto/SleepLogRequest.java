package com.pulsepath.health.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SleepLogRequest {
  @NotNull
  @Min(0)
  private Double hours;

  private String quality;

  public Double getHours() {
    return hours;
  }

  public void setHours(Double hours) {
    this.hours = hours;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality;
  }
}
