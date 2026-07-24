package com.careerhub.job.dto;

import com.careerhub.job.entity.EmploymentType;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String company;
    private String experience;
    private String location;
    private String salary;
    private String description;
    private List<String> skills;
    private EmploymentType employmentType;
    private LocalDateTime postedAt;
}
