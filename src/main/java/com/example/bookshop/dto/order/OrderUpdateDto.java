package com.example.bookshop.dto.order;

import com.example.bookshop.model.Order;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderUpdateDto {
    @NotBlank
    private Order.Status status;
}
