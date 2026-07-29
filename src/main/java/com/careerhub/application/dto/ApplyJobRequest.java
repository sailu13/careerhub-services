package com.careerhub.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyJobRequest {
    @NotNull(message = "Job Id is required")
    private Long jobId;
}
