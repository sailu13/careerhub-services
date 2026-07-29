package com.careerhub.application.service.impl;

import com.careerhub.application.dto.ApplicationResponse;
import com.careerhub.application.dto.ApplyJobRequest;
import com.careerhub.application.entity.Application;
import com.careerhub.application.entity.ApplicationStatus;
import com.careerhub.application.mapper.ApplicationMapper;
import com.careerhub.application.repository.ApplicationRepository;
import com.careerhub.application.service.ApplicationService;
import com.careerhub.auth.entity.User;
import com.careerhub.auth.repository.UserRepository;
import com.careerhub.job.entity.Job;
import com.careerhub.job.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public ApplicationResponse applyJob(Long userId, ApplyJobRequest applyJobRequest){
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not Found"));
        Job job = jobRepository.findById(applyJobRequest.getJobId())
                .orElseThrow(()-> new EntityNotFoundException("Job not Found"));
        applicationRepository.findByUserAndJob(user, job).ifPresent(application -> {
            throw new IllegalStateException("You have already applied for this Job");
        });
        Application application = Application.builder().user(user).job(job).status(ApplicationStatus.APPLIED)
                .appliedAt(LocalDateTime.now()).build();
        Application savedApplication = applicationRepository.save(application);
        return ApplicationMapper.toResponse(savedApplication);
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not Found"));
        return applicationRepository.findByUser(user).stream().map(ApplicationMapper::toResponse).toList();
    }
}
