package com.careerhub.job.service.impl;

import com.careerhub.job.dto.JobResponse;
import com.careerhub.job.entity.EmploymentType;
import com.careerhub.job.entity.Job;
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
    public List<JobResponse> getAllJobs(String search, String location, String employmentType){
        List<Job> jobs;
        boolean hasSearch = search !=null && !search.isBlank();
        boolean hasLocation = location != null && !location.equalsIgnoreCase("All");
        boolean hasEmploymentType = employmentType != null && !employmentType.equalsIgnoreCase("All");
        EmploymentType type = null;

        if (hasEmploymentType){
            String normalized = employmentType.trim().replace(" ", "_")
                    .replace("-", "_").toUpperCase();
            type = EmploymentType.valueOf(normalized);
        }
        if (hasSearch && hasLocation && hasEmploymentType){
            jobs = jobRepository.findByTitleContainingIgnoreCaseAndLocationIgnoreCaseAndEmploymentType(
                    search,location, type);
        } else if (hasLocation && hasSearch) {
            jobs = jobRepository.findByTitleContainingIgnoreCaseAndLocationIgnoreCase(search,location);
        } else  if (hasSearch && hasEmploymentType){
            jobs = jobRepository.findByTitleContainingIgnoreCaseAndEmploymentType(search, type);
        } else if (hasLocation && hasEmploymentType) {
            jobs = jobRepository.findByLocationIgnoreCaseAndEmploymentType(location, type);
        } else if (hasSearch) {
            jobs = jobRepository.findByTitleContainingIgnoreCase(search);
        } else if (hasLocation) {
            jobs = jobRepository.findByLocationIgnoreCase(location);
        } else if (hasEmploymentType) {
            jobs = jobRepository.findByEmploymentType(type);
        } else {
            jobs = jobRepository.findAll();
        }
        return jobs.stream().map(JobMapper::toResponse).toList();
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new RuntimeException("Job not found"));
        return JobMapper.toResponse(job);
    }
}