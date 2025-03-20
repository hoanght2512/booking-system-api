package com.hoanght.bookingsystem.controller;

import com.hoanght.bookingsystem.dto.DataResponse;
import com.hoanght.bookingsystem.dto.PagedResponse;
import com.hoanght.bookingsystem.dto.RoomRequest;
import com.hoanght.bookingsystem.dto.RoomResponse;
import com.hoanght.bookingsystem.service.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Room")
@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<DataResponse<PagedResponse<RoomResponse>>> getAllRooms(Pageable pageable) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully fetch all rooms!", roomService.listAllRooms(pageable)));
    }

    @GetMapping("/available")
    public ResponseEntity<DataResponse<PagedResponse<RoomResponse>>> listAllRooms(@RequestParam("check-in") LocalDateTime checkInDate, @RequestParam("check-out") LocalDateTime checkOutDate, Pageable pageable) {
        return ResponseEntity.ok(DataResponse.ok("Successfully fetch all rooms!", roomService.listAllRoomAvailable(checkInDate, checkOutDate, pageable)));
    }

    @PostMapping
    public ResponseEntity<DataResponse<RoomResponse>> createRoom(@RequestBody @Valid RoomRequest roomRequest) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully create room!", roomService.createRoom(roomRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<RoomResponse>> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully fetch room!", roomService.getRoomById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<RoomResponse>> updateRoom(@PathVariable Long id, @RequestBody @Valid RoomRequest roomRequest) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully update room!", roomService.updateRoom(id, roomRequest)));
    }

    @PutMapping("/available/{id}")
    public ResponseEntity<DataResponse<RoomResponse>> updateRoomAvailable(@PathVariable Long id) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully update room!", roomService.setAvailable(id, true)));
    }

    @PutMapping("/unavailable/{id}")
    public ResponseEntity<DataResponse<RoomResponse>> updateRoomUnavailable(@PathVariable Long id) {
        return ResponseEntity.ok().body(DataResponse.ok("Successfully update room!", roomService.setAvailable(id, false)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().body(DataResponse.ok("Successfully delete room!"));
    }
}
