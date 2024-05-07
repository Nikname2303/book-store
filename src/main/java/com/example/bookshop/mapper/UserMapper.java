package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.UserRequestDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toResponseDto(User user);

    User toModel(UserRequestDto requestDto);

}
