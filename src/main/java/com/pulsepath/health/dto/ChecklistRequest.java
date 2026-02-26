package com.pulsepath.health.dto;

import jakarta.validation.constraints.NotNull;

public class ChecklistRequest {
  @NotNull private Boolean breakfastDone;
  @NotNull private Boolean lunchDone;
  @NotNull private Boolean dinnerDone;
  @NotNull private Boolean skipsDone;
  @NotNull private Boolean pushupsDone;
  @NotNull private Boolean soundSleep;

  public Boolean getBreakfastDone() {
    return breakfastDone;
  }

  public void setBreakfastDone(Boolean breakfastDone) {
    this.breakfastDone = breakfastDone;
  }

  public Boolean getLunchDone() {
    return lunchDone;
  }

  public void setLunchDone(Boolean lunchDone) {
    this.lunchDone = lunchDone;
  }

  public Boolean getDinnerDone() {
    return dinnerDone;
  }

  public void setDinnerDone(Boolean dinnerDone) {
    this.dinnerDone = dinnerDone;
  }

  public Boolean getSkipsDone() {
    return skipsDone;
  }

  public void setSkipsDone(Boolean skipsDone) {
    this.skipsDone = skipsDone;
  }

  public Boolean getPushupsDone() {
    return pushupsDone;
  }

  public void setPushupsDone(Boolean pushupsDone) {
    this.pushupsDone = pushupsDone;
  }

  public Boolean getSoundSleep() {
    return soundSleep;
  }

  public void setSoundSleep(Boolean soundSleep) {
    this.soundSleep = soundSleep;
  }
}
