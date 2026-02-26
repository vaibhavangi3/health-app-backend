package com.pulsepath.health.dto;

import java.util.List;

public class DayResponse {
  private ChecklistResponse checklist;
  private List<SleepLogResponse> sleepLogs;

  public ChecklistResponse getChecklist() {
    return checklist;
  }

  public void setChecklist(ChecklistResponse checklist) {
    this.checklist = checklist;
  }

  public List<SleepLogResponse> getSleepLogs() {
    return sleepLogs;
  }

  public void setSleepLogs(List<SleepLogResponse> sleepLogs) {
    this.sleepLogs = sleepLogs;
  }
}
