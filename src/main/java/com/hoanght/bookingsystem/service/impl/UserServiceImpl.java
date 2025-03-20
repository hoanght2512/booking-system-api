package com.hoanght.bookingsystem.service.impl;

import com.hoanght.bookingsystem.dto.UserRequest;
import com.hoanght.bookingsystem.dto.UserResponse;
import com.hoanght.bookingsystem.entity.User;
import com.hoanght.bookingsystem.exception.ResourceNotFoundException;
import com.hoanght.bookingsystem.repository.UserRepository;
import com.hoanght.bookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserResponse.class)).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(element -> modelMapper.map(element, UserResponse.class)).orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            return modelMapper.map(userRepository.save(user), UserResponse.class);
        }).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
