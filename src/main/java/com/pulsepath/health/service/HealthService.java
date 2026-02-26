package com.pulsepath.health.service;

import com.pulsepath.health.dto.ChecklistRequest;
import com.pulsepath.health.dto.ChecklistResponse;
import com.pulsepath.health.dto.DayResponse;
import com.pulsepath.health.dto.SleepLogRequest;
import com.pulsepath.health.dto.SleepLogResponse;
import com.pulsepath.health.model.DailyChecklist;
import com.pulsepath.health.model.SleepLog;
import com.pulsepath.health.repository.DailyChecklistRepository;
import com.pulsepath.health.repository.SleepLogRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthService {
  private static final int BREAKFAST_PROTEIN = 20;
  private static final int LUNCH_PROTEIN = 15;
  private static final int DINNER_PROTEIN = 18;

  private final DailyChecklistRepository checklistRepository;
  private final SleepLogRepository sleepLogRepository;

  public HealthService(
      DailyChecklistRepository checklistRepository, SleepLogRepository sleepLogRepository) {
    this.checklistRepository = checklistRepository;
    this.sleepLogRepository = sleepLogRepository;
  }

  @Transactional
  public ChecklistResponse upsertChecklist(LocalDate day, ChecklistRequest request) {
    DailyChecklist checklist =
        checklistRepository.findById(day).orElseGet(() -> new DailyChecklist(day));

    checklist.setBreakfastDone(request.getBreakfastDone());
    checklist.setLunchDone(request.getLunchDone());
    checklist.setDinnerDone(request.getDinnerDone());
    checklist.setSkipsDone(request.getSkipsDone());
    checklist.setPushupsDone(request.getPushupsDone());
    checklist.setSoundSleep(request.getSoundSleep());

    DailyChecklist saved = checklistRepository.save(checklist);
    return toChecklistResponse(saved);
  }

  @Transactional(readOnly = true)
  public DayResponse getDay(LocalDate day) {
    Optional<DailyChecklist> checklist = checklistRepository.findById(day);
    List<SleepLog> sleepLogs = sleepLogRepository.findByDayOrderByIdDesc(day);

    DayResponse response = new DayResponse();
    response.setChecklist(checklist.map(this::toChecklistResponse).orElseGet(() -> {
      DailyChecklist empty = new DailyChecklist(day);
      return toChecklistResponse(empty);
    }));
    response.setSleepLogs(sleepLogs.stream().map(this::toSleepLogResponse).collect(Collectors.toList()));
    return response;
  }

  @Transactional
  public SleepLogResponse addSleep(LocalDate day, SleepLogRequest request) {
    SleepLog log = new SleepLog(day, request.getHours(), request.getQuality());
    SleepLog saved = sleepLogRepository.save(log);
    return toSleepLogResponse(saved);
  }

  @Transactional(readOnly = true)
  public List<SleepLogResponse> getSleepLogs(LocalDate day) {
    return sleepLogRepository.findByDayOrderByIdDesc(day).stream()
        .map(this::toSleepLogResponse)
        .collect(Collectors.toList());
  }

  private ChecklistResponse toChecklistResponse(DailyChecklist checklist) {
    ChecklistResponse response = new ChecklistResponse();
    response.setDay(checklist.getDay());
    response.setBreakfastDone(checklist.isBreakfastDone());
    response.setLunchDone(checklist.isLunchDone());
    response.setDinnerDone(checklist.isDinnerDone());
    response.setSkipsDone(checklist.isSkipsDone());
    response.setPushupsDone(checklist.isPushupsDone());
    response.setSoundSleep(checklist.isSoundSleep());
    response.setProteinTotal(calculateProtein(checklist));
    response.setWorkoutsCompleted(calculateWorkouts(checklist));
    return response;
  }

  private int calculateProtein(DailyChecklist checklist) {
    int total = 0;
    if (checklist.isBreakfastDone()) total += BREAKFAST_PROTEIN;
    if (checklist.isLunchDone()) total += LUNCH_PROTEIN;
    if (checklist.isDinnerDone()) total += DINNER_PROTEIN;
    return total;
  }

  private int calculateWorkouts(DailyChecklist checklist) {
    int total = 0;
    if (checklist.isSkipsDone()) total += 1;
    if (checklist.isPushupsDone()) total += 1;
    return total;
  }

  private SleepLogResponse toSleepLogResponse(SleepLog log) {
    SleepLogResponse response = new SleepLogResponse();
    response.setId(log.getId());
    response.setDay(log.getDay());
    response.setHours(log.getHours());
    response.setQuality(log.getQuality());
    return response;
  }
}
