package com.healthapp.precomputed.repository;

import com.healthapp.precomputed.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {}
