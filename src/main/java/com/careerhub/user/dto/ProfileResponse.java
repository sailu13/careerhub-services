package com.careerhub.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;
}