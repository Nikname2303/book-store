package com.example.bookshop.dto.order;

import com.example.bookshop.model.OrderItem;
import java.util.Set;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItem> orderItems;
}
