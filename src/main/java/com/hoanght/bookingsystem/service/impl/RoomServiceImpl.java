package com.hoanght.bookingsystem.service.impl;

import com.hoanght.bookingsystem.dto.RoomRequest;
import com.hoanght.bookingsystem.dto.RoomResponse;
import com.hoanght.bookingsystem.entity.Room;
import com.hoanght.bookingsystem.exception.BadRequestException;
import com.hoanght.bookingsystem.exception.ResourceNotFoundException;
import com.hoanght.bookingsystem.repository.RoomRepository;
import com.hoanght.bookingsystem.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoomResponse> listAllRooms() {
        return roomRepository.findAll().stream().map(room -> modelMapper.map(room, RoomResponse.class)).toList();
    }

    @Override
    public List<RoomResponse> listAllRoomAvailable(String checkIn, String checkOut) {
        LocalDateTime checkInDate = LocalDateTime.parse(checkIn);
        LocalDateTime checkOutDate = LocalDateTime.parse(checkOut);
        if (checkInDate.isAfter(checkOutDate))
            throw new BadRequestException("check in date must be before check out date");
        return roomRepository.findAvailableRooms(checkInDate, checkOutDate).stream().map(room -> modelMapper.map(room, RoomResponse.class)).toList();
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        return roomRepository.findById(id).map(room -> modelMapper.map(room, RoomResponse.class)).orElseThrow(() -> new ResourceNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room newRoom = modelMapper.map(roomRequest, Room.class);
        newRoom.setCode(generateRoomCode(roomRequest.getName()));
        newRoom.setAvailable(true);
        return modelMapper.map(roomRepository.save(newRoom), RoomResponse.class);
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room with id " + id + " not found"));
        room.setName(roomRequest.getName());
        room.setCode(generateRoomCode(roomRequest.getName()));
        room.setDescription(roomRequest.getDescription());
        room.setType(roomRequest.getType());
        room.setCapacity(roomRequest.getCapacity());
        room.setPrice(roomRequest.getPrice());
        room.setAvailable(roomRequest.isAvailable());
        return modelMapper.map(roomRepository.save(room), RoomResponse.class);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @NonNull
    private String generateRoomCode(String roomName) {
        String normalizedRoomName = removeDiacritics(roomName);
        String roomCode = normalizedRoomName.toLowerCase().replace(" ", "-") + "-" + System.currentTimeMillis();

        if (roomRepository.existsByCode(roomCode)) return generateRoomCode(roomName);
        return roomCode;
    }

    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return Pattern.compile("\\p{M}").matcher(normalized).replaceAll("");
    }
}
