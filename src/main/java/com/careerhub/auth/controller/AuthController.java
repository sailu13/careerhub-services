package com.careerhub.auth.controller;

import com.careerhub.auth.dto.*;
import com.careerhub.auth.service.AuthService;
import com.careerhub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = authService.register(request);

        return new ApiResponse<>(
                true,
                "Registration successful",
                response
        );
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> me(Authentication authentication) {

        return new ApiResponse<>(
                true,
                "User fetched successfully",
                authService.getCurrentUser(authentication)
        );
    }
}