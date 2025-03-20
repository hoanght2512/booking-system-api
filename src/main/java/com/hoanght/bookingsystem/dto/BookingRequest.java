package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingRequest {
    @NotBlank
    @JsonProperty("room_code")
    String roomCode;
    @NotBlank
    @JsonProperty("customer_full_name")
    String customerFullName;
    @NotBlank
    @JsonProperty("customer_email")
    String customerEmail;
    @NotBlank
    @JsonProperty("customer_phone")
    String customerPhone;
    @NotBlank
    @JsonProperty("check_in_date")
    LocalDateTime checkInDate;
    @NotBlank
    @JsonProperty("check_out_date")
    LocalDateTime checkOutDate;
}
