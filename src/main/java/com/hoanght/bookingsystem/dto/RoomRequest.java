package com.hoanght.bookingsystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class RoomRequest {
    @NotBlank
    String name;
    @NotNull
    String type;
    @NotNull
    @Min(1)
    Integer capacity;
    @NotBlank
    String description;
    @DecimalMin("0.0")
    BigDecimal price;
    @NotBlank
    String thumbnail;
    @NotBlank
    List<String> images;
}