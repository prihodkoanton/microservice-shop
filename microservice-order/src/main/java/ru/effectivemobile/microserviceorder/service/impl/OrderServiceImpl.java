package ru.effectivemobile.microserviceorder.service.impl;

import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceorder.core.exceptions.*;
import ru.effectivemobile.microserviceorder.core.model.*;
import ru.effectivemobile.microserviceorder.core.requests.OrderItemRequest;
import ru.effectivemobile.microserviceorder.core.requests.OrderRequest;
import ru.effectivemobile.microserviceorder.repository.CustomerRepository;
import ru.effectivemobile.microserviceorder.repository.OrderRepository;
import ru.effectivemobile.microserviceorder.repository.OrganizationRepository;
import ru.effectivemobile.microserviceorder.repository.ProductRepository;
import ru.effectivemobile.microserviceorder.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrganizationRepository organizationRepository;


    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            CustomerRepository customerRepository,
                            OrganizationRepository organizationRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.organizationRepository = organizationRepository;
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + orderRequest.getCustomerId() + " not found.")));
        order.setOrganization(organizationRepository.findById(orderRequest.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with id " + orderRequest.getOrganizationId() + " not found.")));
        order.setStatus(OrderStatus.INPROGRESS);
        order.setCreated(LocalDateTime.now());
        order.setUpdated(LocalDateTime.now());

        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("ProductDto with id " + itemRequest.getProductId() + " not found."));
            if (!product.getPrice().equals(itemRequest.getPrice())) {
                throw new ProductPriceNotMatchedException("Price of product with id " + itemRequest.getProductId() +
                        " doesn't match the price in the catalog.");
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItems.add(orderItem);
        }
        order.setOrderItems(new ArrayList<>(orderItems));

        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found."));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> findByOrganizationId(Long organizationId) {
        return orderRepository.findByOrganizationId(organizationId);
    }

    public List<Order> findByOrderStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedBetween(startDate, endDate);
    }

    public List<Order> findAllByOrderItemContaining(OrderItem orderItem) {
        return orderRepository.findAllByOrderItemsContaining(orderItem);
    }

    public List<Order> findByCustomerAndOrganization(Customer customer, Organization organization) {
        return orderRepository.findByCustomerIdAndOrganizationId(customer, organization);
    }

    public List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status) {
        return orderRepository.findByCustomerIdAndStatus(customer, status);
    }

    public List<Order> findByOrganizationAndStatus(Organization organization, OrderStatus status) {
        return orderRepository.findByOrganizationIdAndStatus(organization, status);
    }

    public List<Order> findByCustomerAndOrganizationAndStatus(Customer customer, Organization organization, OrderStatus status) {
        return orderRepository.findByCustomerIdAndOrganizationIdAndStatus(customer, organization, status);
    }

    @Transactional
    public Order updateOrder(Long id, OrderRequest orderRequest) {
        Order order = findById(id);
        order.setCustomer(customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + orderRequest.getCustomerId() + " not found.")));
        order.setOrganization(organizationRepository.findById(orderRequest.getOrganizationId()).orElseThrow(() -> new OrganizationNotFoundException("Organization with id " + orderRequest.getOrganizationId() + " not found.")));
        order.setStatus(orderRequest.getOrderStatus());
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus newStatus) {
        return orderRepository.updateStatus(id, newStatus);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
