package com.hoanght.bookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ForgotRequest {
    @NotBlank
    String email;
}
