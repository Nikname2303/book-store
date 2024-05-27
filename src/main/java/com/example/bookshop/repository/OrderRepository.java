package com.example.bookshop.repository;

import com.example.bookshop.model.Order;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems.book")
    Set<Order> findAllByUserId(Long id);

    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
