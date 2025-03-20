package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
    String username;
    String email;
    @JsonProperty("full_name")
    String fullName;
    String phone;
}
