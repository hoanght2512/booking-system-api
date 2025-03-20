package com.hoanght.bookingsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomResponse {
    private String code;
    private String name;
    private String type;
    private Integer capacity;
    private String thumbnail;
    private List<String> images;
    private String description;
    private BigDecimal price;
    private boolean available;
}