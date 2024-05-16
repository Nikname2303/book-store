package com.example.bookshop.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private Long id;
    @NotBlank
    private String name;
    private String description;
}
