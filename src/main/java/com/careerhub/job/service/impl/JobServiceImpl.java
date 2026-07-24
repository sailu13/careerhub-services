package com.careerhub.job.service.impl;

import com.careerhub.job.dto.JobResponse;
import com.careerhub.job.mapper.JobMapper;
import com.careerhub.job.repository.JobRepository;
import com.careerhub.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public List<JobResponse> getAllJobs(){
        return jobRepository.findAll().stream().map(JobMapper::toResponse).toList();
    }
}