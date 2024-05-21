package com.example.bookshop.mapper;

import com.example.bookshop.config.MapperConfig;
import com.example.bookshop.dto.cartitem.CartItemRequestDto;
import com.example.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookshop.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "cartItems", source = "cartItems",
            qualifiedByName = "cartItemDtoFromCartItem")
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toModel(CartItemRequestDto requestDto);
}
