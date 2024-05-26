package com.example.bookshop.dto.order;

import com.example.bookshop.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateDto {
    @NotNull
    private Order.Status status;
}
