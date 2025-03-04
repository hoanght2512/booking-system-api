package com.hoanght.bookingsystem.dto;

import com.hoanght.bookingsystem.common.RoomType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RoomRequest {
    @NotBlank
    String name;
    @NotNull
    RoomType type;
    @NotNull
    @Min(1)
    Integer capacity;
    @NotBlank
    String description;
    @DecimalMin("0.0")
    BigDecimal price;
    @NotNull
    boolean available;
}