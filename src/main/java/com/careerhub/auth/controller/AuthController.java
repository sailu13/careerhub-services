package com.careerhub.auth.controller;

import com.careerhub.auth.dto.RegisterRequest;
import com.careerhub.auth.dto.RegisterResponse;
import com.careerhub.auth.service.AuthService;
import com.careerhub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}