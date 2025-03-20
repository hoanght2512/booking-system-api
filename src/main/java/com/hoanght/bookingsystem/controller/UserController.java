package com.hoanght.bookingsystem.controller;

import com.hoanght.bookingsystem.dto.DataResponse;
import com.hoanght.bookingsystem.dto.UserRequest;
import com.hoanght.bookingsystem.dto.UserResponse;
import com.hoanght.bookingsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.ok(DataResponse.ok("Get all users successfully", userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<UserResponse>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(DataResponse.ok("Get user by id successfully", userService.getUserById(id)));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<DataResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(DataResponse.ok("Get user by username successfully", userService.getUserByUsername(username)));
    }

    @PostMapping
    public ResponseEntity<DataResponse<UserResponse>> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(DataResponse.ok("Create user successfully", userService.createUser(userRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(DataResponse.ok("Update user successfully", userService.updateUser(id, userRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(DataResponse.ok("Delete user successfully"));
    }
}
