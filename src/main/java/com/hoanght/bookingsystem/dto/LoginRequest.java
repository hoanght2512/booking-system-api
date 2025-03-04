package com.hoanght.bookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class LoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
