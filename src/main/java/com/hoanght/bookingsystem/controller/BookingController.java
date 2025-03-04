package com.hoanght.bookingsystem.controller;

import com.hoanght.bookingsystem.dto.BookingRequest;
import com.hoanght.bookingsystem.dto.BookingResponse;
import com.hoanght.bookingsystem.dto.DataResponse;
import com.hoanght.bookingsystem.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Booking")
@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<DataResponse<BookingResponse>> bookRoom(@RequestBody @Valid BookingRequest request) {
        return ResponseEntity.ok(DataResponse.ok("Successfully booked room", bookingService.bookRoom(request)));
    }

    @PutMapping("/{bookingId}/confirm")
    public ResponseEntity<DataResponse<BookingResponse>> confirmBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(DataResponse.ok("Successfully confirmed booking", bookingService.confirmBooking(bookingId)));
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<DataResponse<BookingResponse>> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(DataResponse.ok("Successfully cancelled booking", bookingService.cancelBooking(bookingId)));
    }
}
