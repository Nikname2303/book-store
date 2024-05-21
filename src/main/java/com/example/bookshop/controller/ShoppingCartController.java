package com.example.bookshop.controller;

import com.example.bookshop.dto.cartitem.CartItemRequestDto;
import com.example.bookshop.dto.cartitem.CartItemUpdateDto;
import com.example.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookshop.model.User;
import com.example.bookshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCart(user.getId());
    }

    @PostMapping
    public ShoppingCartResponseDto addItemToCart(@RequestBody @Valid CartItemRequestDto requestDto,
                                                 Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addCartItem(requestDto, user.getId());
    }

    @PutMapping("/cart-item/{id}")
    public ShoppingCartResponseDto update(@PathVariable Long id,
                                          Authentication authentication,
                                          @RequestBody @Valid CartItemUpdateDto updateDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateByCartItemId(id, user.getId(), updateDto);
    }

    @DeleteMapping("/cart-item/{id}")
    public void deleteById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
