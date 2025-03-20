package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Value;

@Value
public class UserRequest {
    String username;
    String password;
    @Email
    String email;
    @JsonProperty("full_name")
    String fullName;
    String phone;
}
