package ru.effectivemobile.microserviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorder.core.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemByProductId(Long productId);
    List<OrderItem> findByQuantityGreaterThan (Integer quantity);
    List<OrderItem> findOrderItemByOrderId (Long orderId);
}
