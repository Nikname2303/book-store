package com.example.bookshop.repository;

import com.example.bookshop.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"user", "user.roles"})
    List<Order> getAllByUserId(Long id, Pageable pageable);

    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
