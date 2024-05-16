package com.example.bookshop.service;

import com.example.bookshop.dto.book.BookResponseDto;
import com.example.bookshop.dto.category.CategoryRequestDto;
import com.example.bookshop.dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    void updateById(Long id, CategoryRequestDto categoryRequestDto);

    void deleteById(Long id);

    public List<BookResponseDto> getBooksByCategoryId(Long id);
}
