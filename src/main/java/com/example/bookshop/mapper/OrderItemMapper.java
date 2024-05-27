package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.model.CartItem;
import com.example.bookshop.model.OrderItem;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface OrderItemMapper {
    Set<OrderItemResponseDto> toSetDto(Set<OrderItem> orderItems);

    OrderItemResponseDto toDto(OrderItem orderItem);

    Set<OrderItem> toOrderItems(List<CartItem> cartItems);
}
