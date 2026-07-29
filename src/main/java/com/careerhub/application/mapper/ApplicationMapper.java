package com.careerhub.application.mapper;

import com.careerhub.application.dto.ApplicationResponse;
import com.careerhub.application.entity.Application;

public class ApplicationMapper {
    private ApplicationMapper(){}

    public static ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder().id(application.getId()).jobId(application.getJob().getId())
                .jobTitle(application.getJob().getTitle()).company(application.getJob().getCompany())
                .status(application.getStatus()).appliedAt(application.getAppliedAt()).build();
    }
}
