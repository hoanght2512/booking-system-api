package com.hoanght.bookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingRequest {
    @NotBlank
    String roomCode;
    @NotBlank
    String customerFullName;
    @NotBlank
    String customerEmail;
    @NotBlank
    String customerPhone;
    @NotBlank
    LocalDateTime checkInDate;
    @NotBlank
    LocalDateTime checkOutDate;
}
