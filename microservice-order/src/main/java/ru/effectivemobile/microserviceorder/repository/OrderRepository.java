package ru.effectivemobile.microserviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.effectivemobile.microserviceorder.core.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrganizationId(Long organizationId);

    List<Order> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findAllByOrderItemsContaining(OrderItem orderItem);

    List<Order> findByCustomerIdAndOrganizationId(Customer customer, Organization organization);

    List<Order> findByCustomerIdAndStatus(Customer customer, OrderStatus status);

    List<Order> findByOrganizationIdAndStatus(Organization organization, OrderStatus status);

    @Query(value = "select * from orders o where o.customer_id =?1 AND o.organization_id =?2 AND o.status=?3", nativeQuery = true)
    List<Order> findByCustomerIdAndOrganizationIdAndStatus(Customer customer, Organization organization, OrderStatus status);

    @Query(value = "UPDATE orders o set o.status =?2 where o.id =?1", nativeQuery = true)
    Order updateStatus(Long id, OrderStatus status);
}