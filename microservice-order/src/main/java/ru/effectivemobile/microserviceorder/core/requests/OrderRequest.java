package ru.effectivemobile.microserviceorder.core.requests;

import ru.effectivemobile.microserviceorder.core.model.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
