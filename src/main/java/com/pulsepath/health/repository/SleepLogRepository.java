package com.pulsepath.health.repository;

import com.pulsepath.health.model.SleepLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepLogRepository extends JpaRepository<SleepLog, Long> {
  List<SleepLog> findByDayOrderByIdDesc(LocalDate day);
}
