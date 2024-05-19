package com.example.bookshop.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

@Getter
@Setter
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
    @NotNull
    private List<Long> categoryIds;
}
