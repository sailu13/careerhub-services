package com.careerhub.application.service;

import com.careerhub.application.dto.ApplicationResponse;
import com.careerhub.application.dto.ApplyJobRequest;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse applyJob(Long userId, ApplyJobRequest applyJobRequest);

    List<ApplicationResponse> getMyApplications(Long userId);
}
