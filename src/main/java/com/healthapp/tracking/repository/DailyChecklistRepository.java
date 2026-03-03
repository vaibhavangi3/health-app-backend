package com.healthapp.tracking.repository;

import com.healthapp.tracking.entity.DailyChecklist;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyChecklistRepository extends JpaRepository<DailyChecklist, LocalDate> {}
