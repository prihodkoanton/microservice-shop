package ru.effectivemobile.microserviceorder.service;

import ru.effectivemobile.microserviceorder.core.model.*;
import ru.effectivemobile.microserviceorder.core.requests.OrderRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequest orderRequest);
    Order findById(Long id);

    List<Order> findAll();
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByOrganizationId(Long organizationId);
    List<Order> findByOrderStatus(OrderStatus status);
    List<Order> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findAllByOrderItemContaining(OrderItem orderItem);
    List<Order> findByCustomerAndOrganization(Customer customer, Organization organization);
    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
    List<Order> findByOrganizationAndStatus(Organization organization, OrderStatus status);
    List<Order> findByCustomerAndOrganizationAndStatus(Customer customer, Organization organization, OrderStatus status);
    Order updateOrder(Long id, OrderRequest orderRequest);

    Order updateOrderStatus(Long id, OrderStatus newStatus);

    void delete(Long id);
    void delete (Order order);
}
