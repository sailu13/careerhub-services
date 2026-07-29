package com.careerhub.application.dto;

import com.careerhub.application.entity.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String company;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}
