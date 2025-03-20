package com.hoanght.bookingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ForgotRequest {
    @NotBlank
    @Email
    String email;
}
