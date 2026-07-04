package com.careerhub.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
}