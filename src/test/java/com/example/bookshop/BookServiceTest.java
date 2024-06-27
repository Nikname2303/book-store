package com.example.bookshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.example.bookshop.dto.book.BookResponseDto;
import com.example.bookshop.dto.book.CreateBookRequestDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Category;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.service.impl.BookServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void save_ValidData_Success() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setCategoryIds(List.of(1L, 2L));

        Category category1 = new Category();
        category1.setId(1L);

        Category category2 = new Category();
        category2.setId(2L);

        Book book = new Book();
        book.setId(1L);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(1L);

        when(categoryRepository.findAllById(requestDto.getCategoryIds())).thenReturn(List.of(category1, category2));
        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        BookResponseDto result = bookService.save(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void save_CategoryNotFound_ThrowsEntityNotFoundException() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setCategoryIds(List.of(1L, 2L));

        when(categoryRepository.findAllById(requestDto.getCategoryIds())).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> bookService.save(requestDto));
    }

    @Test
    void findById_ValidId_ReturnsBookResponseDto() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        BookResponseDto result = bookService.findById(bookId);

        assertEquals(responseDto, result);
    }

    @Test
    void findById_InvalidId_ThrowsEntityNotFoundException() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.findById(bookId));
    }

    @Test
    void findAll_ReturnsListOfBookResponseDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Book book = new Book();
        book.setId(1L);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(1L);

        Page<Book> page = new PageImpl<>(List.of(book));

        when(bookRepository.findAll(pageable)).thenReturn(page);
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        List<BookResponseDto> result = bookService.findAll(pageable);

        assertEquals(List.of(responseDto), result);
    }

    @Test
    void deleteById_ValidId_Success() {
        Long bookId = 1L;

        bookService.deleteById(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void updateById_ValidData_Success() {
        Long bookId = 1L;
        CreateBookRequestDto requestDto = new CreateBookRequestDto();

        Book book = new Book();
        book.setId(bookId);

        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        bookService.updateById(bookId, requestDto);

        verify(bookMapper, times(1)).toModel(requestDto);
        verify(bookRepository, times(1)).save(book);
    }
}
