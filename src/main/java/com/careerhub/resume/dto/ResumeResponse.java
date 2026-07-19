package com.careerhub.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResumeResponse {
    private long id;
    private String fileName;
    private String fileType;
    private long fileSize;
    private LocalDateTime updatedAt;
    private String message;
}
