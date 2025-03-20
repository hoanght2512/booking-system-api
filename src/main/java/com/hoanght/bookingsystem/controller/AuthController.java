package com.hoanght.bookingsystem.controller;

import com.hoanght.bookingsystem.dto.*;
import com.hoanght.bookingsystem.security.CustomUserDetails;
import com.hoanght.bookingsystem.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<DataResponse<AuthResponse>> signIn(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(DataResponse.ok("Sign in successfully", authService.signIn(loginRequest)));
    }

    @PostMapping("/signup")
    public ResponseEntity<DataResponse<Void>> signUp(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
        return ResponseEntity.ok(DataResponse.ok("Sign up successfully"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<DataResponse<Void>> forgotPassword(@RequestBody @Valid ForgotRequest forgotRequest) {
        authService.forgotPassword(forgotRequest);
        return ResponseEntity.ok(DataResponse.ok("Email has been sent to reset password"));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-password")
    public ResponseEntity<DataResponse<Void>> changePassword(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        authService.changePassword(userDetails, changePasswordRequest);
        return ResponseEntity.ok(DataResponse.ok("Change password successfully"));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<DataResponse<UserResponse>> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(DataResponse.ok("Get profile successfully", authService.getProfile(userDetails)));
    }
}
