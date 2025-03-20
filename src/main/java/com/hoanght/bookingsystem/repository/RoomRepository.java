package com.hoanght.bookingsystem.repository;

import com.hoanght.bookingsystem.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("""
                SELECT r FROM Room r WHERE r.available = true
                AND NOT EXISTS (
                    SELECT 1 FROM Booking b WHERE b.room.id = r.id
                    AND (b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate)
                )
            """)
    Page<Room> findAvailableRooms(@Param("checkInDate") LocalDateTime checkInDate, @Param("checkOutDate") LocalDateTime checkOutDate, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Room> getRoomByCode(String code);

    boolean existsByCode(String roomCode);

    boolean existsByName(String name);
}