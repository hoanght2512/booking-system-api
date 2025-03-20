package com.hoanght.bookingsystem.service.impl;

import com.hoanght.bookingsystem.common.RoomType;
import com.hoanght.bookingsystem.dto.PagedResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public PagedResponse<RoomResponse> listAllRooms(Pageable pageable) {
        Page<Room> rooms = roomRepository.findAll(pageable);
        return new PagedResponse<>(rooms.getContent().stream().map(e -> modelMapper.map(e, RoomResponse.class)).toList(), rooms);
    }

    @Override
    public PagedResponse<RoomResponse> listAllRoomAvailable(LocalDateTime checkInDate, LocalDateTime checkOutDate, Pageable pageable) {
        if (checkInDate.isAfter(checkOutDate))
            throw new BadRequestException("check in date must be before check out date");
        Page<Room> rooms = roomRepository.findAvailableRooms(checkInDate, checkOutDate, pageable);
        return new PagedResponse<>(rooms.getContent().stream().map(e -> modelMapper.map(e, RoomResponse.class)).toList(), rooms);
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        return roomRepository.findById(id).map(room -> modelMapper.map(room, RoomResponse.class)).orElseThrow(() -> new ResourceNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room newRoom = modelMapper.map(roomRequest, Room.class);
        newRoom.setCode(generateRoomCode(roomRequest.getName()));
        return modelMapper.map(roomRepository.save(newRoom), RoomResponse.class);
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        if (roomRepository.existsByName(roomRequest.getName()))
            throw new BadRequestException("Room with name " + roomRequest.getName() + " already exists");
        return roomRepository.findById(id).map(room -> {
            room.setName(roomRequest.getName());
            room.setCode(generateRoomCode(roomRequest.getName()));
            room.setDescription(roomRequest.getDescription());
            room.setType(RoomType.valueOf(roomRequest.getType()));
            room.setCapacity(roomRequest.getCapacity());
            room.setPrice(roomRequest.getPrice());
            return modelMapper.map(roomRepository.save(room), RoomResponse.class);
        }).orElseThrow(() -> new ResourceNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public RoomResponse setAvailable(Long id, Boolean available) {
        return roomRepository.findById(id).map(room -> {
            room.setAvailable(available);
            return modelMapper.map(roomRepository.save(room), RoomResponse.class);
        }).orElseThrow(() -> new ResourceNotFoundException("Room with id " + id + " not found"));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @NonNull
    private String generateRoomCode(String roomName) {
        String normalizedRoomName = removeDiacritics(roomName);
        String roomCode = normalizedRoomName.toLowerCase().replace(" ", "-");
        if (roomRepository.existsByCode(roomCode)) return generateRoomCode(roomName) + "-" + System.currentTimeMillis();
        return roomCode;
    }

    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return Pattern.compile("\\p{M}").matcher(normalized).replaceAll("");
    }
}
