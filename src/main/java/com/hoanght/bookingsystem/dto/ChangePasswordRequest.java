package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ChangePasswordRequest {
    @JsonProperty("old_password")
    String oldPassword;
    @JsonProperty("new_password")
    String newPassword;
}
