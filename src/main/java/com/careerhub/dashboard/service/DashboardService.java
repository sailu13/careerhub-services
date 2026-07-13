package com.careerhub.dashboard.service;

import com.careerhub.auth.entity.User;
import com.careerhub.auth.repository.UserRepository;
import com.careerhub.dashboard.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;

    public DashboardResponse getDashboard(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new DashboardResponse(
                user.getFirstName() + " " + user.getLastName(),
                0,
                0,
                1,
                20
        );
    }
}