package com.example.bookshop.repository;

import com.example.bookshop.model.OrderItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId "
            + "AND oi.order.user.id = :userId AND oi.id = :itemId")
    Optional<OrderItem> findOrderItemByUserIdAndOrderIdAndItemId(@Param("userId") Long userId,
                                                                 @Param("orderId") Long orderId,
                                                                 @Param("itemId") Long itemId);
}
