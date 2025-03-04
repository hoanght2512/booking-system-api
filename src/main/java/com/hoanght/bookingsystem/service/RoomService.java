package com.hoanght.bookingsystem.service;

import com.hoanght.bookingsystem.dto.RoomRequest;
import com.hoanght.bookingsystem.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> listAllRooms();

    List<RoomResponse> listAllRoomAvailable(String checkIn, String checkOut);

    RoomResponse getRoomById(Long id);

    RoomResponse createRoom(RoomRequest roomRequest);

    RoomResponse updateRoom(Long id, RoomRequest roomRequest);

    void deleteRoom(Long id);
}
