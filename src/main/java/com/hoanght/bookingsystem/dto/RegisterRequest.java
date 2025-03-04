package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class RegisterRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String email;
    @NotBlank
    @JsonProperty("full_name")
    String fullName;
    @NotBlank
    String phone;
}
