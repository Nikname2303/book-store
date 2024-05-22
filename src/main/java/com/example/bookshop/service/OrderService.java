package com.example.bookshop.service;

import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.model.Order;
import java.util.Set;

public interface OrderService {
    Set<OrderResponseDto> getOrders(Long id);

    Set<OrderItemResponseDto> getAllOrderItemsById(Long userId, Long orderId);

    OrderResponseDto updateAddress(Long id, String address);

    OrderResponsePatchDto updateStatus(String email, Long orderId, Order.Status status);

    OrderItemResponseDto getOrderItemById(String email, Long orderId, Long itemId);
}
