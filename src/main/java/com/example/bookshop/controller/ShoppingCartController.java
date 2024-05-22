package com.example.bookshop.controller;

import com.example.bookshop.dto.cartitem.CartItemRequestDto;
import com.example.bookshop.dto.cartitem.CartItemUpdateDto;
import com.example.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookshop.model.User;
import com.example.bookshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ShoppingCart management", description = "Endpoints for managing shoppingCart")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Get shoppingCart", description = "Get shoppingCart for current user")
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCart(user.getId());
    }

    @PostMapping
    @Operation(summary = "Add item to cart", description = "Add one item to cart for current user")
    public ShoppingCartResponseDto addItemToCart(@RequestBody @Valid CartItemRequestDto requestDto,
                                                 Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addCartItem(requestDto, user.getId());
    }

    @PutMapping("/cart-item/{id}")
    @Operation(summary = "update cart", description = "Update quantity")
    public ShoppingCartResponseDto update(@PathVariable Long id,
                                          Authentication authentication,
                                          @RequestBody @Valid CartItemUpdateDto updateDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateByCartItemId(id, user.getId(), updateDto);
    }

    @DeleteMapping("/cart-item/{id}")
    @Operation(summary = "delete by id", description =
            "Delete by id cartItem. Don't use soft delete, so be careful")
    public void deleteById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
