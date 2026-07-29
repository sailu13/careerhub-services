package com.careerhub.application.repository;

import com.careerhub.application.entity.Application;
import com.careerhub.auth.entity.User;
import com.careerhub.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserAndJob(User user, Job job);
    List<Application> findByUser(User user);
    List<Job> findByJob(Job job);
}
