package com.careerhub.resume.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResumeDetailsResponse {
     private Long id;
     private String fileName;
     private String fileType;
     private Long fileSize;
     private LocalDateTime updatedAt;
}
