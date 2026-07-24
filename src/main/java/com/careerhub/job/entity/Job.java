package com.careerhub.job.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String experience;
    private String location;
    private String salary;
    @Column(length = 3000)
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    @Builder.Default
    private List<String> skills = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
    private LocalDateTime postedAt;
}