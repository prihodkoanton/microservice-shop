package ru.effectivemobile.microserviceorder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.effectivemobile.microserviceorder.core.dto.OrderDTO;
import ru.effectivemobile.microserviceorder.core.dto.UserCredentials;
import ru.effectivemobile.microserviceorder.core.model.Order;
import ru.effectivemobile.microserviceorder.core.model.OrderStatus;
import ru.effectivemobile.microserviceorder.core.requests.OrderRequest;
import ru.effectivemobile.microserviceorder.service.OrderService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = OrderController.BASE_URL)
public class OrderController {

    private final OrderService orderService;

    public static final String BASE_URL = "/api/v1/orders";

    private final RestTemplate restTemplate;

    @Value("${spring.authenticationServiceUrl.value}")
    private String authenticationServiceUrl;

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate =restTemplate;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserCredentials credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCredentials> request = new HttpEntity<>(credentials, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(authenticationServiceUrl + "/api/v1/authenticate", request, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping()
    public List<Order> getOrders() {
        List<Order> orders = orderService.findAll();
        return orders;
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(OrderDTO.toOrderDTO(createdOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderDTO.toOrderDTO(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        Order updatedOrder = orderService.updateOrder(id, orderRequest);
        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderDTO.toOrderDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable Long customerId) {
        List<Order> orders = orderService.findByCustomerId(customerId);
        if (orders == null || orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orders.stream()
                .map(OrderDTO::toOrderDTO)
                .collect(Collectors.toList());
    }

    @PatchMapping("/updateOrderStatus/{id}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderDTO.toOrderDTO(updatedOrder));
    }
}
