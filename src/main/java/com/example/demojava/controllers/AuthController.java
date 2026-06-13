package com.example.demojava.controllers;

import com.example.demojava.dto.LoginRequest;
import com.example.demojava.dto.RegisterRequest;
import com.example.demojava.models.ApiResponse;
import com.example.demojava.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        String result = authService.register(request);
        ApiResponse<String> response = new ApiResponse<>("Success", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@Valid @RequestBody LoginRequest request) {
        Map<String, String> loginData = authService.login(request);
        ApiResponse<Map<String,String>> response = new ApiResponse<>("Success", loginData);

        return  ResponseEntity.ok(response);
    }
}
