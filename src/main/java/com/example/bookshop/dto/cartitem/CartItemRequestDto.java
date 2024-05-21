package com.example.bookshop.dto.cartitem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    private Long bookId;
    private int quantity;
}
