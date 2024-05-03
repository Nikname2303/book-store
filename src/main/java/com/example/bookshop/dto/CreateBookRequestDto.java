package com.example.bookshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @ISBN
    @NotNull
    private String isbn;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    private String description;
    private String coverImage;
}
