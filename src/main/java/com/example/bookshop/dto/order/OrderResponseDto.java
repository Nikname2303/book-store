package com.example.bookshop.dto.order;

import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private BigDecimal total;
    private Set<OrderItemResponseDto> orderItems;
}
