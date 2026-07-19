package com.careerhub.resume.entity;


import com.careerhub.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private long fileSize;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime uploadedAt;
}
