package com.example.bookshop.service.impl;

import com.example.bookshop.dto.book.BookDtoWithoutCategoryIds;
import com.example.bookshop.dto.category.CategoryRequestDto;
import com.example.bookshop.dto.category.CategoryResponseDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.mapper.CategoryMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Category;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toResponseDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found category with this id: " + id)
        );
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toModel(categoryRequestDto);
        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Override
    public void updateById(Long id, CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Can't find book with id: " + id);
        }
        Category category = categoryMapper.toModel(categoryRequestDto);
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Can't find book with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id) {
        List<Book> books = bookRepository.findAllByCategoriesId(id);
        return bookMapper.toDtoWithoutCategoriesId(books);
    }
}
