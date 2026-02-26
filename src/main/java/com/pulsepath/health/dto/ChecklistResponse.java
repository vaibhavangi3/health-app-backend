package com.pulsepath.health.dto;

import java.time.LocalDate;

public class ChecklistResponse {
  private LocalDate day;
  private boolean breakfastDone;
  private boolean lunchDone;
  private boolean dinnerDone;
  private boolean skipsDone;
  private boolean pushupsDone;
  private boolean soundSleep;
  private int proteinTotal;
  private int workoutsCompleted;

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

  public int getProteinTotal() {
    return proteinTotal;
  }

  public void setProteinTotal(int proteinTotal) {
    this.proteinTotal = proteinTotal;
  }

  public int getWorkoutsCompleted() {
    return workoutsCompleted;
  }

  public void setWorkoutsCompleted(int workoutsCompleted) {
    this.workoutsCompleted = workoutsCompleted;
  }
}
