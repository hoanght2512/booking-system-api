package com.hoanght.bookingsystem.service.impl;

import com.hoanght.bookingsystem.common.Role;
import com.hoanght.bookingsystem.dto.*;
import com.hoanght.bookingsystem.entity.User;
import com.hoanght.bookingsystem.exception.BadRequestException;
import com.hoanght.bookingsystem.exception.ResourceNotFoundException;
import com.hoanght.bookingsystem.repository.UserRepository;
import com.hoanght.bookingsystem.security.CustomUserDetails;
import com.hoanght.bookingsystem.security.jwt.JwtProvider;
import com.hoanght.bookingsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public void signUp(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new BadRequestException("Username is already in use");
        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new BadRequestException("Email is already in use");
        if (userRepository.existsByPhone(registerRequest.getPhone()))
            throw new BadRequestException("Phone is already in use");

        User user = modelMapper.map(registerRequest, User.class);
        user.setUsername(registerRequest.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of(Role.CUSTOMER));
        userRepository.save(user);
    }

    @Override
    public AuthResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return AuthResponse.builder()
                .accessToken(jwtProvider.generateToken(userDetails.getUsername()))
                .username(userDetails.getUsername())
                .fullName(userDetails.getUser().getFullName())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void forgotPassword(ForgotRequest forgotRequest) {
        // send link and token to email
    }

    @Override
    public void changePassword(CustomUserDetails userDetails, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword()))
            throw new BadRequestException("Old password is incorrect");
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public UserResponse getProfile(CustomUserDetails userDetails) {
        return UserResponse.builder()
                .username(userDetails.getUsername())
                .fullName(userDetails.getUser().getFullName())
                .email(userDetails.getUser().getEmail())
                .phone(userDetails.getUser().getPhone())
                .build();
    }
}
