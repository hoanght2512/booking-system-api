package com.hoanght.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingResponse {
    @JsonProperty("booking_code")
    private String status;
    @JsonProperty("room_code")
    private String roomCode;
    @JsonProperty("room_name")
    private String roomName;
    @JsonProperty("room_type")
    private String roomType;
    @JsonProperty("room_capacity")
    private Integer roomCapacity;
    @JsonProperty("room_thumbnail")
    private String roomThumbnail;
    @JsonProperty("room_images")
    private List<String> roomImages;
    @JsonProperty("room_description")
    private String roomDescription;
    @JsonProperty("room_price")
    private BigDecimal roomPrice;
    @JsonProperty("room_available")
    private boolean roomAvailable;
    @JsonProperty("customer_full_name")
    private String customerFullName;
    @JsonProperty("customer_email")
    private String customerEmail;
    @JsonProperty("customer_phone")
    private String customerPhone;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("check_in_date")
    private LocalDateTime checkInDate;
    @JsonProperty("check_out_date")
    private LocalDateTime checkOutDate;
}
