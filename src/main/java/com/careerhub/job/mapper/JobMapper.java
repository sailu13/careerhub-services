package com.careerhub.job.mapper;

import com.careerhub.job.dto.JobResponse;
import com.careerhub.job.entity.Job;

public class JobMapper {
    private JobMapper(){}

    public static JobResponse toResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId()).title(job.getTitle()).company(job.getCompany()).experience(job.getExperience())
                .location(job.getLocation()).salary(job.getSalary()).description(job.getDescription()).skills(job.getSkills())
                .employmentType(job.getEmploymentType()).postedAt(job.getPostedAt()).build();
    }
}
