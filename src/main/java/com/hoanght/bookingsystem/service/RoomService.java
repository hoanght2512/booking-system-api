package com.hoanght.bookingsystem.service;

import com.hoanght.bookingsystem.dto.PagedResponse;
import com.hoanght.bookingsystem.dto.RoomRequest;
import com.hoanght.bookingsystem.dto.RoomResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface RoomService {
    PagedResponse<RoomResponse> listAllRooms(Pageable pageable);

    PagedResponse<RoomResponse> listAllRoomAvailable(LocalDateTime checkInDate, LocalDateTime checkOutDate, Pageable pageable);

    RoomResponse getRoomById(Long id);

    RoomResponse createRoom(RoomRequest roomRequest);

    RoomResponse updateRoom(Long id, RoomRequest roomRequest);

    RoomResponse setAvailable(Long id, Boolean available);

    void deleteRoom(Long id);
}
