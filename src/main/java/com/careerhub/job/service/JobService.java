package com.careerhub.job.service;

import com.careerhub.job.dto.JobResponse;

import java.util.List;

public interface JobService {

    List<JobResponse> getAllJobs(String search, String location, String employmentType);

    JobResponse getJobById(Long id);
}
