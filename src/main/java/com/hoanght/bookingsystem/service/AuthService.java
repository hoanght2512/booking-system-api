package com.hoanght.bookingsystem.service;

import com.hoanght.bookingsystem.dto.*;
import com.hoanght.bookingsystem.security.CustomUserDetails;

public interface AuthService {
    void signUp(RegisterRequest registerRequest);

    AuthResponse signIn(LoginRequest loginRequest);

    void forgotPassword(ForgotRequest forgotRequest);

    void changePassword(CustomUserDetails userDetails, ChangePasswordRequest changePasswordRequest);

    UserResponse getProfile(CustomUserDetails userDetails);
}
