package com.example.bookshop.repository;

import com.example.bookshop.model.Order;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findAllByUserId(Long id);

    Order getOrderByUserEmail(String email);
}
