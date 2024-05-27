package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.order.OrderResponseDto;
import com.example.bookshop.dto.order.OrderResponsePatchDto;
import com.example.bookshop.model.Order;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    Set<OrderResponseDto> toSetDto(Set<Order> orders);

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toDto(Order order);

    @Mapping(source = "user.id", target = "userId")
    OrderResponsePatchDto toPatchDto(Order order);
}
