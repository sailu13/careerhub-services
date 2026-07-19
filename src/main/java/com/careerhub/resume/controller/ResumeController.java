package com.careerhub.resume.controller;

import com.careerhub.common.response.ApiResponse;
import com.careerhub.resume.dto.ResumeDetailsResponse;
import com.careerhub.resume.dto.ResumeResponse;
import com.careerhub.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ResumeResponse> uploadResume(Authentication authentication, @RequestParam("file")MultipartFile file)
        throws Exception {

        ResumeResponse response = resumeService.uploadResume(authentication, file);
        return new ApiResponse<>(true, "Resume Uploaded Successfully", response);
    }

    @GetMapping
    public ApiResponse<ResumeDetailsResponse> getResume(Authentication authentication) {
        return new ApiResponse<>(true, "Resume fetched Successfully", resumeService.getResume(authentication));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadResume(Authentication authentication) throws IOException {
        return resumeService.downloadResume(authentication);
    }

    @DeleteMapping
    public ApiResponse<String> deleteResume(Authentication authentication) throws IOException {
        resumeService.deleteResume(authentication);
        return new ApiResponse<>(true, "Resume deleted Successfully", null);
    }
}
