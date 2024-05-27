package com.example.bookshop.service.impl;

import com.example.bookshop.dto.order.OrderRequestDto;
import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.OrderItemMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.model.ShoppingCart;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.CartItemRepository;
import com.example.bookshop.repository.OrderItemRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.ShoppingCartRepository;
import com.example.bookshop.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

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
    @Transactional
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
        orderRepository.save(order);
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

    @Override
    public OrderResponseDto createNewOrder(User user, OrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.getCartByUserId(user.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't found user with this id: "
                        + user.getId())
        );
        Order order = createOrder(user, requestDto);
        Set<OrderItem> orderItems = getOrderItems(shoppingCart, order);
        BigDecimal total = getTotal(orderItems);
        order.setTotal(total);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        return orderMapper.toDto(order);
    }

    private Order createOrder(User user, OrderRequestDto requestDto) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.IN_PROGRESS);
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    private Set<OrderItem> getOrderItems(ShoppingCart shoppingCart, Order order) {
        return shoppingCart.getCartItems()
                .stream()
                .map(e -> new OrderItem(
                        order,
                        e.getBook(),
                        e.getQuantity(),
                        e.getBook().getPrice()))
                .collect(Collectors.toSet());
    }

    private BigDecimal getTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(e -> e.getPrice()
                        .multiply(BigDecimal.valueOf(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
