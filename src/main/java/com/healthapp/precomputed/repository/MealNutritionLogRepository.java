package com.healthapp.precomputed.repository;

import com.healthapp.precomputed.entity.MealNutritionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealNutritionLogRepository extends JpaRepository<MealNutritionLog, Long> {}
