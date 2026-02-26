package com.pulsepath.health.controller;

import com.pulsepath.health.dto.ChecklistRequest;
import com.pulsepath.health.dto.ChecklistResponse;
import com.pulsepath.health.dto.DayResponse;
import com.pulsepath.health.dto.SleepLogRequest;
import com.pulsepath.health.dto.SleepLogResponse;
import com.pulsepath.health.service.HealthService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {
  private final HealthService healthService;

  public HealthController(HealthService healthService) {
    this.healthService = healthService;
  }

  @GetMapping("/health")
  public String health() {
    return "ok";
  }

  @GetMapping("/days/{day}")
  public DayResponse getDay(
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day) {
    return healthService.getDay(day);
  }

  @PutMapping("/days/{day}/checklist")
  public ChecklistResponse upsertChecklist(
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
      @Valid @RequestBody ChecklistRequest request) {
    return healthService.upsertChecklist(day, request);
  }

  @PostMapping("/days/{day}/sleep")
  public SleepLogResponse addSleep(
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
      @Valid @RequestBody SleepLogRequest request) {
    return healthService.addSleep(day, request);
  }

  @GetMapping("/days/{day}/sleep")
  public List<SleepLogResponse> getSleepLogs(
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day) {
    return healthService.getSleepLogs(day);
  }
}
