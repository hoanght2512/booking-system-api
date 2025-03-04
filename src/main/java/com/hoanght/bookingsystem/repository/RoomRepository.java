package com.hoanght.bookingsystem.repository;

import com.hoanght.bookingsystem.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("""
            SELECT r FROM Room r WHERE r.available = true AND r.code
            NOT IN (SELECT b.room.code FROM Booking b
            WHERE (b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate))
            """)
    List<Room> findAvailableRooms(@Param("checkInDate") LocalDateTime checkInDate, @Param("checkOutDate") LocalDateTime checkOutDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Room> getRoomByCode(String code);

    boolean existsByCode(String roomCode);
}