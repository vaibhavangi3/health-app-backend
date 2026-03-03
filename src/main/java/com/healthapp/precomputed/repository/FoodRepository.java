package com.healthapp.precomputed.repository;

import com.healthapp.precomputed.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {}
