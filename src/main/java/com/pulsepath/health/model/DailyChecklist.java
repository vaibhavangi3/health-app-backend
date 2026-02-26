package com.pulsepath.health.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "daily_checklists")
public class DailyChecklist {
  @Id
  @Column(name = "day", nullable = false)
  private LocalDate day;

  @Column(name = "breakfast_done", nullable = false)
  private boolean breakfastDone;

  @Column(name = "lunch_done", nullable = false)
  private boolean lunchDone;

  @Column(name = "dinner_done", nullable = false)
  private boolean dinnerDone;

  @Column(name = "skips_done", nullable = false)
  private boolean skipsDone;

  @Column(name = "pushups_done", nullable = false)
  private boolean pushupsDone;

  @Column(name = "sound_sleep", nullable = false)
  private boolean soundSleep;

  public DailyChecklist() {}

  public DailyChecklist(LocalDate day) {
    this.day = day;
  }

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public boolean isBreakfastDone() {
    return breakfastDone;
  }

  public void setBreakfastDone(boolean breakfastDone) {
    this.breakfastDone = breakfastDone;
  }

  public boolean isLunchDone() {
    return lunchDone;
  }

  public void setLunchDone(boolean lunchDone) {
    this.lunchDone = lunchDone;
  }

  public boolean isDinnerDone() {
    return dinnerDone;
  }

  public void setDinnerDone(boolean dinnerDone) {
    this.dinnerDone = dinnerDone;
  }

  public boolean isSkipsDone() {
    return skipsDone;
  }

  public void setSkipsDone(boolean skipsDone) {
    this.skipsDone = skipsDone;
  }

  public boolean isPushupsDone() {
    return pushupsDone;
  }

  public void setPushupsDone(boolean pushupsDone) {
    this.pushupsDone = pushupsDone;
  }

  public boolean isSoundSleep() {
    return soundSleep;
  }

  public void setSoundSleep(boolean soundSleep) {
    this.soundSleep = soundSleep;
  }
}
