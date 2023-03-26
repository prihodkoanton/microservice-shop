package ru.effectivemobile.microserviceorder.core.requests;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.effectivemobile.microserviceorder.core.model.OrderStatus;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderRequest {

    private Long customerId;
    private Long organizationId;
    private OrderStatus orderStatus;
    private List<OrderItemRequest> orderItems;
}
