package com.healthapp.auth.controller;

import com.healthapp.auth.dto.LoginRequest;
import com.healthapp.auth.dto.LoginResponse;
import com.healthapp.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final String QA_USERNAME = "qa";
  private static final String QA_PASSWORD = "qa";
  private static final String QA_TOKEN = "qa-token";

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
    if (QA_USERNAME.equals(request.getUsername()) && QA_PASSWORD.equals(request.getPassword())) {
      return ResponseEntity.ok(ApiResponse.ok(new LoginResponse(QA_USERNAME, QA_TOKEN)));
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error("invalid credentials"));
  }
}
