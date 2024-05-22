package com.example.bookshop.dto.order;

import com.example.bookshop.model.Order;
import lombok.Data;

@Data
public class OrderUpdateDto {
    private Order.Status status;
}
