package ru.effectivemobile.microserviceorder.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorder.core.model.Order;
import ru.effectivemobile.microserviceorder.core.model.OrderItem;
import ru.effectivemobile.microserviceorder.core.model.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDTO {

    private Long id;
    private Order orderId;
    private Product productId;
    private Integer quantity;

    public OrderItem toOrderItem() {
        OrderItem item = new OrderItem();
        item.setId(this.id);
        item.setOrder(this.orderId);
        item.setProduct(this.productId);
        item.setQuantity(this.quantity);
        return item;
    }

    public static OrderItemDTO fromOrderItem(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrder());
        dto.setProductId(item.getProduct());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public static List<OrderItem> toOrderItemList(List<OrderItemDTO> dtos) {
        List<OrderItem> items = new ArrayList<>();
        return items;
    }

    public static List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> orderItems) {
        List<OrderItemDTO> dtos = new ArrayList<>();
        return dtos;
    }
}
