package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.category.CategoryRequestDto;
import com.example.bookshop.dto.category.CategoryResponseDto;
import com.example.bookshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryResponseDto toResponseDto(Category category);

    Category toModel(CategoryRequestDto categoryRequestDto);
}
