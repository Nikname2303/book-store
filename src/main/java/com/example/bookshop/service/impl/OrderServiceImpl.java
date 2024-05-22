package com.example.bookshop.service.impl;

import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.OrderItemMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;

    @Override
    public Set<OrderResponseDto> getOrders(Long id) {
        Set<Order> orders = orderRepository.findAllByUserId(id);
        return orderMapper.toSetDto(orders);
    }

    @Override
    public Set<OrderItemResponseDto> getAllOrderItemsById(Long userId, Long orderId) {
        Set<Order> orders = orderRepository.findAllByUserId(userId);
        for (Order o : orders) {
            if (o.getId().equals(orderId)) {
                return orderItemMapper.toSetDto(o.getOrderItems());
            }
        }
        throw new EntityNotFoundException("Can't find order with this id: " + orderId);
    }

    @Override
    public OrderResponseDto updateAddress(Long id, String address) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotal(BigDecimal.valueOf(0));
        order.setUser(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user with id: " + id)
        ));
        order.setStatus(Order.Status.IN_PROGRESS);
        order.setShippingAddress(address);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderResponsePatchDto updateStatus(String email, Long orderId, Order.Status status) {
        Order order = orderRepository.getOrderByUserEmail(email);
        order.setStatus(status);
        return orderMapper.toPatchDto(order);
    }

    @Override
    public OrderItemResponseDto getOrderItemById(String email, Long orderId, Long itemId) {
        Order order = orderRepository.getOrderByUserEmail(email);
        Set<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem oi : orderItems) {
            if (oi.getId().equals(itemId)) {
                return orderItemMapper.toDto(oi);
            }
        }
        throw new EntityNotFoundException("Can't find order item with this id: " + itemId);
    }
}
