package com.careerhub.resume.repository;

import com.careerhub.auth.entity.User;
import com.careerhub.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByUser(User user);
}
