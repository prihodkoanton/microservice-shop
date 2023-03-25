package ru.effectivemobile.microserviceorder.core.requests;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderItemRequest {

    private Long productId;
    private BigDecimal price;
    private int quantity;
}
