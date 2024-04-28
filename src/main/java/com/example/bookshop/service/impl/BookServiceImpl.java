package com.example.bookshop.service.impl;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CreateBookRequestDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found book with id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, CreateBookRequestDto requestDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (requestDto.getCoverImage() != null) {
                book.setCoverImage(requestDto.getCoverImage());
            }
            if (requestDto.getDescription() != null) {
                book.setDescription(requestDto.getDescription());
            }
            if (requestDto.getPrice() != null) {
                book.setPrice(requestDto.getPrice());
            }
            if (requestDto.getIsbn() != null) {
                book.setIsbn(requestDto.getIsbn());
            }
            if (requestDto.getTitle() != null) {
                book.setTitle(requestDto.getTitle());
            }
            if (requestDto.getAuthor() != null) {
                book.setAuthor(requestDto.getAuthor());
            }
            bookRepository.save(book);
        } else {
            throw new EntityNotFoundException("Can't update non exist book");
        }
    }
}
