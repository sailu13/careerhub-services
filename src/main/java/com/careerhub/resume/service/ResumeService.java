package com.careerhub.resume.service;

import com.careerhub.auth.entity.User;
import com.careerhub.auth.repository.UserRepository;
import com.careerhub.resume.dto.ResumeDetailsResponse;
import com.careerhub.resume.dto.ResumeResponse;
import com.careerhub.resume.entity.Resume;
import com.careerhub.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeResponse uploadResume(Authentication authentication,
                                       MultipartFile file) throws IOException {

        // Find Logged-in User
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate File Type
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/pdf")
                        || contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            throw new RuntimeException("Only PDF and DOCX files are allowed.");
        }

        // Validate File Size (Max 5 MB)
        long maxSize = 5 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new RuntimeException("File size should not exceed 5 MB.");
        }

        // Create uploads folder if it doesn't exist
        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Delete previous resume (if any)
        Resume existingResume = resumeRepository.findByUser(user).orElse(null);
        if (existingResume != null) {
            Files.deleteIfExists(Paths.get(existingResume.getFilePath()));
            resumeRepository.delete(existingResume);
        }

        // Generate unique filename
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Save file to uploads folder
        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        // Save metadata to database
        Resume resume = Resume.builder()
                .fileName(fileName)
                .fileType(contentType)
                .fileSize(file.getSize())
                .filePath(filePath.toString())
                .uploadedAt(LocalDateTime.now())
                .user(user)
                .build();

        Resume savedResume = resumeRepository.save(resume);

        return new ResumeResponse(
                savedResume.getId(),
                savedResume.getFileName(),
                savedResume.getFileType(),
                savedResume.getFileSize(),
                savedResume.getUploadedAt(),
                "Resume uploaded successfully"
        );
    }

    public ResumeDetailsResponse getResume(Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Resume resume = resumeRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Resume not found"));

        return new ResumeDetailsResponse(resume.getId(), resume.getFileName(),resume.getFileType(),
                resume.getFileSize(), resume.getUploadedAt());
    }

    public ResponseEntity<Resource> downloadResume(Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new RuntimeException("User not Found"));
        Resume resume = resumeRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Resume not Found"));

        Path path =Paths.get(resume.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(resume.getFileType())).body(resource);
    }

    public void deleteResume(Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Resume resume = resumeRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Resume not found"));
        Files.deleteIfExists(Paths.get(resume.getFilePath()));
        resumeRepository.delete(resume);
    }
}
