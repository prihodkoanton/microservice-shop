package ru.effectivemobile.microserviceorganization.service;

import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.Product;
import ru.effectivemobile.microserviceorganization.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product findById(Long id);

    List<Product> findByName(String name);

    List<Product> findByDiscription(String discription);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByOrganizationAndStatusProduct(Organization organization, ProductStatus productStatus);

    List<Product> findByOrganization(Organization organization);
}
