package com.example.bookshop.service;

import com.example.bookshop.dto.cartitem.CartItemRequestDto;
import com.example.bookshop.dto.cartitem.CartItemUpdateDto;
import com.example.bookshop.dto.shoppingcart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCart(Long id);

    ShoppingCartResponseDto addCartItem(CartItemRequestDto requestDto, Long id);

    ShoppingCartResponseDto updateByCartItemId(
            Long cartItemId,
            Long id,
            CartItemUpdateDto updateDto);

    void deleteById(Long id);
}
