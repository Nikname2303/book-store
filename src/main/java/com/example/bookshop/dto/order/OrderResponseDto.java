package com.example.bookshop.dto.order;

import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import java.util.Set;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemResponseDto> orderItems;
}
