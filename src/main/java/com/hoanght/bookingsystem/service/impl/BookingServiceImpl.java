package com.hoanght.bookingsystem.service.impl;

import com.hoanght.bookingsystem.common.BookingStatus;
import com.hoanght.bookingsystem.dto.BookingRequest;
import com.hoanght.bookingsystem.dto.BookingResponse;
import com.hoanght.bookingsystem.entity.Booking;
import com.hoanght.bookingsystem.entity.Customer;
import com.hoanght.bookingsystem.entity.Room;
import com.hoanght.bookingsystem.exception.BadRequestException;
import com.hoanght.bookingsystem.exception.ResourceNotFoundException;
import com.hoanght.bookingsystem.repository.BookingRepository;
import com.hoanght.bookingsystem.repository.CustomerRepository;
import com.hoanght.bookingsystem.repository.RoomRepository;
import com.hoanght.bookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingResponse bookRoom(BookingRequest request) {
        if (request.getCheckInDate().isAfter(request.getCheckOutDate()))
            throw new BadRequestException("check in date must be before check out date");
        Room room = roomRepository.getRoomByCode(request.getRoomCode()).orElseThrow(() -> new ResourceNotFoundException("Room with code " + request.getRoomCode() + " not found"));
        if (bookingRepository.existsByRoomAndDateOverlap(room.getCode(), request.getCheckInDate(), request.getCheckOutDate()))
            throw new BadRequestException("room is not available");

        Customer customer = modelMapper.map(request, Customer.class);
        customerRepository.saveAndFlush(customer);

        Booking booking = new Booking();
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.PENDING);
        bookingRepository.saveAndFlush(booking);

        return modelMapper.map(booking, BookingResponse.class);
    }

    @Override
    public BookingResponse confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking with id " + bookingId + " not found"));
        if (booking.getStatus() == BookingStatus.CONFIRMED)
            throw new BadRequestException("Booking is already confirmed");
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.saveAndFlush(booking);

        return modelMapper.map(booking, BookingResponse.class);
    }

    @Override
    public BookingResponse cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking with id " + bookingId + " not found"));
        if (booking.getStatus() == BookingStatus.CANCELLED)
            throw new BadRequestException("Booking is already cancelled");
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.saveAndFlush(booking);

        return modelMapper.map(booking, BookingResponse.class);
    }

    @Scheduled(fixedRate = 60000) // Chạy mỗi 1 phút
    @Transactional
    public void cancelExpiredBookings() {
        LocalDateTime now = LocalDateTime.now().minusMinutes(10);
        List<Booking> expiredBookings = bookingRepository.findAllByStatusAndCreatedAtBefore(BookingStatus.PENDING, now);
        for (Booking booking : expiredBookings) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        }
    }
}
