package com.pulsepath.health.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "sleep_logs")
public class SleepLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "day", nullable = false)
  private LocalDate day;

  @Column(name = "hours", nullable = false)
  private double hours;

  @Column(name = "quality")
  private String quality;

  public SleepLog() {}

  public SleepLog(LocalDate day, double hours, String quality) {
    this.day = day;
    this.hours = hours;
    this.quality = quality;
  }

  public Long getId() {
    return id;
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
