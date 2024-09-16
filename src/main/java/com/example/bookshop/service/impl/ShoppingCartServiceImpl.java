package com.example.bookshop.service.impl;

import com.example.bookshop.dto.cartitem.CartItemRequestDto;
import com.example.bookshop.dto.cartitem.CartItemUpdateDto;
import com.example.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.CartItemMapper;
import com.example.bookshop.mapper.ShoppingCartMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.CartItem;
import com.example.bookshop.model.ShoppingCart;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.CartItemRepository;
import com.example.bookshop.repository.ShoppingCartRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    private final BookRepository bookRepository;

    @Override
    public ShoppingCartResponseDto getCart(Long id) {
        ShoppingCart cart = shoppingCartRepository.getCartByUserId(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart for user with id: "
                        + id)
        );
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    public ShoppingCartResponseDto addCartItem(CartItemRequestDto requestDto, Long id) {
        ShoppingCart cart = shoppingCartRepository.getCartByUserId(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart for user with id: "
                        + id)
        );
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(
                () -> new RuntimeException("Book with id "
                        + requestDto.getBookId()
                        + " doesn't exist")
        );
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(cart);
        cartItem.setBook(book);
        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        return getCart(id);
    }

    @Override
    public ShoppingCartResponseDto updateByCartItemId(
            Long cartItemId,
            Long id,
            CartItemUpdateDto updateDto
    ) {
        ShoppingCart shoppingCart = shoppingCartRepository.getCartByUserId(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart for user with id: "
                        + id)
        );
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(e -> e.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't found cart with this id: "
                                + id));
        cartItem.setQuantity(updateDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {
        if (cartItemRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Can't found item with this id: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }
}
