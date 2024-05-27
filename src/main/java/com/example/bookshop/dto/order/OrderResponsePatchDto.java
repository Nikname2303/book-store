package com.example.bookshop.dto.order;

import com.example.bookshop.model.Order;
import lombok.Data;

@Data
public class OrderResponsePatchDto {
    private Long id;
    private Long userId;
    private Order.Status status;
}
