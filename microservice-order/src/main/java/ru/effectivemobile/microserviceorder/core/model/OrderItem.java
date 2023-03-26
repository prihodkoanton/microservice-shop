package ru.effectivemobile.microserviceorder.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;
}
