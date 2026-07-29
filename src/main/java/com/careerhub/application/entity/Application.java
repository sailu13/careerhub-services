package com.careerhub.application.entity;

import com.careerhub.auth.entity.User;
import com.careerhub.job.entity.Job;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "application", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "job_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}
