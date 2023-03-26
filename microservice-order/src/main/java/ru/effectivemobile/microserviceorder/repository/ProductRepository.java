package ru.effectivemobile.microserviceorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorder.core.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByDescription(String description);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}
