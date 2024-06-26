package com.example.bookshop.repository;

import com.example.bookshop.model.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> getAllByShoppingCartId(Long shoppingCartId);
}
