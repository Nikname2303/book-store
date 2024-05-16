package com.example.bookshop.controller;

import com.example.bookshop.dto.book.BookResponseDto;
import com.example.bookshop.dto.category.CategoryRequestDto;
import com.example.bookshop.dto.category.CategoryResponseDto;
import com.example.bookshop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Get a list with all categories")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "Create a new category", description = "Create a new category in DB")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryResponseDto createCategory(@RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(summary = "Get category by id", description = "Get one category by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Update category by id", description = "For updating you need give all fields")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategoryById(@PathVariable Long id,
                                   @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        categoryService.updateById(id, categoryRequestDto);
    }

    @Operation(summary = "Delete category by id", description = "Delete category by ID. Use soft delete")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "Get book by category", description = "Get list books by category Id")
    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookResponseDto> getBooksByCategory(@PathVariable Long id) {
        return categoryService.getBooksByCategoryId(id);
    }
}
