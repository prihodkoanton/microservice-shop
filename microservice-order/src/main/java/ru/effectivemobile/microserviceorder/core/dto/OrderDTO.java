package ru.effectivemobile.microserviceorder.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorder.core.model.Order;
import ru.effectivemobile.microserviceorder.core.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private Long id;
    private CustomerDTO customer;
    private OrganizationDTO organization;
    private OrderStatus orderStatus;
    private LocalDateTime created;
    private LocalDateTime updated;

    private List<OrderItemDTO> orderItems = new ArrayList<>();

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomer(CustomerDTO.toCustomer(orderDTO.getCustomer()));
        order.setOrganization(OrganizationDTO.toOrganization(orderDTO.getOrganization()));
        order.setStatus(orderDTO.getOrderStatus());
        order.setCreated(orderDTO.getCreated());
        order.setUpdated(orderDTO.getUpdated());
        order.setOrderItems(OrderItemDTO.toOrderItemList(orderDTO.getOrderItems()));
        return order;
    }

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomer(CustomerDTO.toCustomerDTO(order.getCustomer()));
        dto.setOrganization(OrganizationDTO.toOrganizationDTO(order.getOrganization()));
        dto.setOrderStatus(order.getStatus());
        dto.setCreated(order.getCreated());
        dto.setUpdated(order.getUpdated());
        dto.setOrderItems(OrderItemDTO.toOrderItemDTOList(order.getOrderItems()));
        return dto;
    }
}
