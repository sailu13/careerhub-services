package com.careerhub.dashboard.controller;

import com.careerhub.common.response.ApiResponse;
import com.careerhub.dashboard.dto.DashboardResponse;
import com.careerhub.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardResponse> getDashboard(Authentication authentication) {

        return new ApiResponse<>(
                true,
                "Dashboard loaded successfully",
                dashboardService.getDashboard(authentication)
        );
    }
}