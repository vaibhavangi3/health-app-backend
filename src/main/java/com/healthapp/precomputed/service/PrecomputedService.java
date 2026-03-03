package com.healthapp.precomputed.service;

import com.healthapp.precomputed.dto.ExerciseCreateRequest;
import com.healthapp.precomputed.dto.FoodCreateRequest;
import com.healthapp.precomputed.entity.Exercise;
import com.healthapp.precomputed.entity.Food;
import com.healthapp.precomputed.repository.ExerciseRepository;
import com.healthapp.precomputed.repository.FoodRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrecomputedService {
  private final ExerciseRepository exerciseRepository;
  private final FoodRepository foodRepository;

  public PrecomputedService(ExerciseRepository exerciseRepository, FoodRepository foodRepository) {
    this.exerciseRepository = exerciseRepository;
    this.foodRepository = foodRepository;
  }

  @Transactional(readOnly = true)
  public List<Exercise> listExercises() {
    return exerciseRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<Food> listFoods() {
    return foodRepository.findAll();
  }

  @Transactional
  public Exercise createExercise(Long userId, ExerciseCreateRequest request) {
    Exercise exercise = new Exercise();
    exercise.setName(request.getName());
    exercise.setCategory(request.getCategory());
    exercise.setMuscleGroup(request.getMuscleGroup());
    exercise.setCalorieBurnPerSet(request.getCalorieBurnPerSet());
    exercise.setCreatedBy(userId);
    return exerciseRepository.save(exercise);
  }

  @Transactional
  public Food createFood(Long userId, FoodCreateRequest request) {
    Food food = new Food();
    food.setFoodName(request.getFoodName());
    food.setCarbs(request.getCarbs());
    food.setProtein(request.getProtein());
    food.setFats(request.getFats());
    food.setCreatedBy(userId);
    return foodRepository.save(food);
  }
}
