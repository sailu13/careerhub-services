package com.careerhub.user.service;


import com.careerhub.auth.entity.User;
import com.careerhub.auth.repository.UserRepository;
import com.careerhub.user.dto.ProfileResponse;
import com.careerhub.user.dto.UpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public ProfileResponse getProfile(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return ProfileResponse.builder().id(user.getId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail()).role(user.getRole().name()).build();
    }

    public ProfileResponse updateProfile(String email, UpdateProfileRequest request){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User updatedUser = userRepository.save(user);

        return ProfileResponse.builder().id(updatedUser.getId()).firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName()).email(updatedUser.getEmail()).role(updatedUser.getRole().name()).build();
    }
}
