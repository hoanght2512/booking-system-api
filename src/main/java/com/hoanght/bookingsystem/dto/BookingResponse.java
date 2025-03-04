package com.hoanght.bookingsystem.dto;

import com.hoanght.bookingsystem.common.BookingStatus;
import com.hoanght.bookingsystem.common.RoomType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class BookingResponse {
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    BookingStatus status;
    String roomCode;
    String roomName;
    RoomType roomType;
    Integer roomCapacity;
    String roomThumbnail;
    List<String> roomImages;
    String roomDescription;
    BigDecimal roomPrice;
    String customerFullName;
    String customerEmail;
    String customerPhone;
    LocalDateTime checkInDate;
    LocalDateTime checkOutDate;
}
