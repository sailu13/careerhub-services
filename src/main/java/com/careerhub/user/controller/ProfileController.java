package com.careerhub.user.controller;

import com.careerhub.common.response.ApiResponse;
import com.careerhub.user.dto.ProfileResponse;
import com.careerhub.user.dto.UpdateProfileRequest;
import com.careerhub.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ApiResponse<ProfileResponse> getProfile(Authentication authentication){
        ProfileResponse response = profileService.getProfile(authentication.getName());

        return new ApiResponse<>(true,"Profile fetched Successfully", response);
    }

    @PutMapping
    public ApiResponse<ProfileResponse> updateProfile(Authentication authentication,
                                                      @Valid @RequestBody UpdateProfileRequest request){
        ProfileResponse response = profileService.updateProfile(authentication.getName(), request);

        return new ApiResponse<>(true, "Profile Updated Successful", response);
    }
}
