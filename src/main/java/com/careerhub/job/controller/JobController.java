package com.careerhub.job.controller;

import com.careerhub.common.response.ApiResponse;
import com.careerhub.job.dto.JobResponse;
import com.careerhub.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ApiResponse<List<JobResponse>> getAllJobs() {
        return new ApiResponse<>(
                true,
                "Jobs fetched successfully",
                jobService.getAllJobs()
        );
    }
}
