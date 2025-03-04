package com.hoanght.bookingsystem.dto;

import com.hoanght.bookingsystem.common.RoomType;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class RoomResponse {
    String code;
    String name;
    RoomType type;
    Integer capacity;
    String thumbnail;
    List<String> images;
    String description;
    BigDecimal price;
    boolean available;
}