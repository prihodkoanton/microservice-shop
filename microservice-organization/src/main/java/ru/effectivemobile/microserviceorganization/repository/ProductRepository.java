package ru.effectivemobile.microserviceorganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.Product;
import ru.effectivemobile.microserviceorganization.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrganizationAndProductStatus(Organization organization, ProductStatus productStatus);

    List<Product> findByOrganization(Organization organization);

    Optional<Product> findByIdAndOrganization(Long id, Organization organization);

    void deleteByIdAndOrganization(Long id, Organization organization);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByName(String name);

    List<Product> findByDescription(String description);
}
