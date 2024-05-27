package com.example.bookshop.service;

import com.example.bookshop.dto.order.OrderRequestDto;
import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.User;
import java.util.Set;

public interface OrderService {
    Set<OrderResponseDto> getAll(Long id);

    Set<OrderItemResponseDto> getAllOrderItemsById(Long userId, Long orderId);

    OrderResponseDto updateAddress(Long id, String address);

    OrderResponsePatchDto updateStatus(Long userId, Long orderId, Order.Status status);

    OrderItemResponseDto getOrderItemById(Long userId, Long orderId, Long itemId);

    OrderResponseDto createNewOrder(User user, OrderRequestDto requestDto);
}
