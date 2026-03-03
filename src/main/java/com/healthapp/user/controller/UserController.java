package com.healthapp.user.controller;

import com.healthapp.common.response.ApiResponse;
import com.healthapp.user.dto.UserCreateRequest;
import com.healthapp.user.dto.UserResponse;
import com.healthapp.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserCreateRequest request) {
    UserResponse response = userService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("created", response));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.ok(userService.getById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserResponse>>> list() {
    return ResponseEntity.ok(ApiResponse.ok(userService.list()));
  }
}
