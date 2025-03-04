package com.hoanght.bookingsystem.service;

import com.hoanght.bookingsystem.dto.BookingRequest;
import com.hoanght.bookingsystem.dto.BookingResponse;

public interface BookingService {

    BookingResponse bookRoom(BookingRequest request);

    BookingResponse confirmBooking(Long bookingId);

    BookingResponse cancelBooking(Long bookingId);
}
