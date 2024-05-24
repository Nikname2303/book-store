package com.example.bookshop.service.impl;

import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.OrderItemMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.repository.OrderItemRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.service.OrderService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<OrderResponseDto> getAll(Long userId) {
        Set<Order> orders = orderRepository.findAllByUserId(userId);
        return orderMapper.toSetDto(orders);
    }

    @Override
    public Set<OrderItemResponseDto> getAllOrderItemsById(Long userId, Long orderId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(
                () -> new EntityNotFoundException("Can`t find order for user by this id: " + userId)
        );
        return orderItemMapper.toSetDto(order.getOrderItems());
    }

    @Override
    public OrderResponseDto updateAddress(Long userId, String address) {
        Order order = orderRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find user with this id: " + userId)
        );
        order.setShippingAddress(address);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponsePatchDto updateStatus(Long userId, Long orderId, Order.Status status) {
        Order order = orderRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order for user with this id: "
                        + userId)
        );
        order.setStatus(status);
        return orderMapper.toPatchDto(order);
    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long userId, Long orderId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findOrderItemByUserIdAndOrderIdAndItemId(userId,
                        orderId,
                        itemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order item with this id: " + itemId));
        return orderItemMapper.toDto(orderItem);
    }
}
