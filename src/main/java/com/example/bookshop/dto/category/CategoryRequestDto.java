package com.example.bookshop.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CategoryRequestDto {
    @NotBlank
    @Length(max = 50)
    private String name;
    @Length(max = 255)
    private String description;
}
