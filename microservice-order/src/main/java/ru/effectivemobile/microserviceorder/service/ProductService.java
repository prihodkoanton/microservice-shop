package ru.effectivemobile.microserviceorder.service;


import ru.effectivemobile.microserviceorder.core.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {

    Product getById(Long id);

    List<Product> getAll();
    List<Product> getByName(String name);
    List<Product> getByDescription(String description);
    List<Product> getByPrice(BigDecimal price);
    List<Product> getByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    Product create(Product product);

    Product update(Long id, Product product);

}
