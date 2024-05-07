package com.example.bookshop.service;

import com.example.bookshop.dto.UserRegistrationRequestDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
