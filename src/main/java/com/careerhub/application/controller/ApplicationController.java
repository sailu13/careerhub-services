package com.careerhub.application.controller;

import com.careerhub.application.dto.ApplicationResponse;
import com.careerhub.application.dto.ApplyJobRequest;
import com.careerhub.application.service.ApplicationService;
import com.careerhub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApiResponse<ApplicationResponse> applyJob(@Valid @RequestBody ApplyJobRequest applyJobRequest) {
        Long loggedInUserId = 1L;
        return new ApiResponse<>(true,"Application Submitted Successfully",
                applicationService.applyJob(loggedInUserId, applyJobRequest));
    }

    @GetMapping("/myApplication")
    public ApiResponse<List<ApplicationResponse>> getMyApplication() {
        Long loggedUserId = 1L;
        return new ApiResponse<>(true, "Application Fetched Successfully",
                applicationService.getMyApplications(loggedUserId));
    }
}
