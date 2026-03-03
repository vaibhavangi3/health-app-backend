package com.healthapp.user.service;

import com.healthapp.user.dto.UserCreateRequest;
import com.healthapp.user.dto.UserResponse;
import com.healthapp.user.entity.User;
import com.healthapp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public UserResponse create(UserCreateRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());

    User saved = userRepository.save(user);
    return toResponse(saved);
  }

  @Transactional(readOnly = true)
  public UserResponse getById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
    return toResponse(user);
  }

  @Transactional(readOnly = true)
  public List<UserResponse> list() {
    return userRepository.findAll().stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  private UserResponse toResponse(User user) {
    UserResponse response = new UserResponse();
    response.setId(user.getId());
    response.setName(user.getName());
    response.setEmail(user.getEmail());
    response.setCreatedAt(user.getCreatedAt());
    response.setUpdatedAt(user.getUpdatedAt());
    return response;
  }
}
