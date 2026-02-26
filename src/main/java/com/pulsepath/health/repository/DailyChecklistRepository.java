package com.pulsepath.health.repository;

import com.pulsepath.health.model.DailyChecklist;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyChecklistRepository extends JpaRepository<DailyChecklist, LocalDate> {}
