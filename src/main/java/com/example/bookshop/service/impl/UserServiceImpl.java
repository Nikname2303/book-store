package com.example.bookshop.service.impl;

import com.example.bookshop.dto.user.UserRegistrationRequestDto;
import com.example.bookshop.dto.user.UserResponseDto;
import com.example.bookshop.exception.RegistrationException;
import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User is already exist");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encodedPassword);
        User savedUser = userRepository.save(userMapper.toModelFromRegisterDto(requestDto));
        return userMapper.toResponseDto(savedUser);
    }
}
