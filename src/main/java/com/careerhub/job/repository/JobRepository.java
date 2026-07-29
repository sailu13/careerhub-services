package com.careerhub.job.repository;

import com.careerhub.job.entity.EmploymentType;
import com.careerhub.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    List<Job> findByTitleContainingIgnoreCase(String keyword);
    List<Job> findByLocationIgnoreCase(String location);
    List<Job> findByEmploymentType(com.careerhub.job.entity.EmploymentType employmentType);
    List<Job> findByTitleContainingIgnoreCaseAndLocationIgnoreCase(String keyword, String location);
    List<Job> findByTitleContainingIgnoreCaseAndEmploymentType(String keyword,
                                                               com.careerhub.job.entity.EmploymentType employmentType);
    List<Job> findByLocationIgnoreCaseAndEmploymentType(String location,
                                                        com.careerhub.job.entity.EmploymentType employmentType);
    List<Job> findByTitleContainingIgnoreCaseAndLocationIgnoreCaseAndEmploymentType(
            String keyword, String location, com.careerhub.job.entity.EmploymentType employmentType);

    Optional<Job> findById(Long id);
}
