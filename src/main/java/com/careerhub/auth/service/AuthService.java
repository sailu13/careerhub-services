package com.careerhub.auth.service;

import com.careerhub.auth.dto.LoginRequest;
import com.careerhub.auth.dto.LoginResponse;
import com.careerhub.auth.dto.RegisterRequest;
import com.careerhub.auth.dto.RegisterResponse;
import com.careerhub.auth.entity.User;
import com.careerhub.auth.repository.UserRepository;
import com.careerhub.common.exception.ResourceAlreadyExistsException;
import com.careerhub.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        System.out.println("==================================");
        System.out.println("Input Email      : " + request.getEmail());
        System.out.println("DB Email         : " + user.getEmail());
        System.out.println("Input Password   : " + request.getPassword());
        System.out.println("Stored Password  : " + user.getPassword());

        boolean matched = passwordEncoder.matches(request.getPassword(), user.getPassword());

        System.out.println("Password Matches : " + matched);
        System.out.println("==================================");

        if (!matched) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                "Bearer",
                user.getEmail()
        );
    }
}