package com.pulsepath.health.dto;

import java.time.LocalDate;

public class SleepLogResponse {
  private Long id;
  private LocalDate day;
  private double hours;
  private String quality;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public double getHours() {
    return hours;
  }

  public void setHours(double hours) {
    this.hours = hours;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality;
  }
}
