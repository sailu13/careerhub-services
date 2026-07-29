package com.careerhub.job.controller;

import com.careerhub.common.response.ApiResponse;
import com.careerhub.job.dto.JobResponse;
import com.careerhub.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ApiResponse<List<JobResponse>> getAllJobs(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "All") String location,
            @RequestParam(required = false, defaultValue = "All") String employmentType) {
        List<JobResponse> jobs = jobService.getAllJobs(search,location,employmentType);
        return new ApiResponse<>(true, "Jobs fetched successfully", jobs);
    }

    @GetMapping("/{id}")
    public ApiResponse<JobResponse> getJobById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Job fetched Successfully", jobService.getJobById(id));
    }
}
