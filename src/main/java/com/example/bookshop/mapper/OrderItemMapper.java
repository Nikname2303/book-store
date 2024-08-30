package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.orderitem.OrderItemResponseDto;
import com.example.bookshop.model.CartItem;
import com.example.bookshop.model.OrderItem;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    Set<OrderItemResponseDto> toSetDto(Set<OrderItem> orderItems);

    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponseDto toDto(OrderItem orderItem);

    Set<OrderItem> toOrderItems(List<CartItem> cartItems);
}
