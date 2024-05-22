package com.example.bookshop.controller;

import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.User;
import com.example.bookshop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get orders", description = "Get Set orders for current user")
    public Set<OrderResponseDto> getOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrders(user.getId());
    }

    @PostMapping
    @Operation(summary = "Create new order", description =
            "Creating new order. For that you need only shippingAddress")
    public OrderResponseDto createNewOrder(
            Authentication authentication,
            @RequestBody String shippingAddress) {
        User user = (User) authentication.getPrincipal();
        return orderService.updateAddress(user.getId(), shippingAddress);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Upgrade status", description = "Upgrade status by id for current user")
    public OrderResponsePatchDto upgradeStatus(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody Order.Status status) {
        User user = (User) authentication.getPrincipal();
        return orderService.updateStatus(user.getEmail(), id, status);
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get order items by id", description =
            "Get Set order items for current user")
    public Set<OrderItemResponseDto> getOrderItemsById(
            Authentication authentication,
            @PathVariable Long orderId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrderItemsById(user.getId(), orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item by id", description =
            "Get one item from order by id for current user")
    public OrderItemResponseDto getOrderItemById(
            Authentication authentication,
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderItemById(user.getEmail(), orderId, itemId);
    }
}
