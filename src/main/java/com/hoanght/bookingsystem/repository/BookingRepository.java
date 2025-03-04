package com.hoanght.bookingsystem.repository;

import com.hoanght.bookingsystem.common.BookingStatus;
import com.hoanght.bookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByStatusAndCreatedAtBefore(BookingStatus status, LocalDateTime time);

    @Query("""
            SELECT COUNT(b) > 0 FROM Booking b
            WHERE b.room.code = :roomCode
            AND (:checkInDate < b.checkOutDate AND :checkOutDate > b.checkInDate)
            """)
    boolean existsByRoomAndDateOverlap(@Param("roomCode") String roomCode, @Param("checkInDate") LocalDateTime checkInDate, @Param("checkOutDate") LocalDateTime checkOutDate);

    Optional<Booking> getBookingById(Long id);
}