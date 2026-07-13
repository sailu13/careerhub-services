package com.careerhub.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private String fullName;
    private Integer applications;
    private Integer interviews;
    private Integer resumes;
    private Integer profileCompletion;
}