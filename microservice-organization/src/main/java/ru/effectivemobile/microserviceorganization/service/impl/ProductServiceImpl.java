package ru.effectivemobile.microserviceorganization.service.impl;

import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceorganization.model.Organization;
import ru.effectivemobile.microserviceorganization.model.Product;
import ru.effectivemobile.microserviceorganization.model.ProductStatus;
import ru.effectivemobile.microserviceorganization.repository.ProductRepository;
import ru.effectivemobile.microserviceorganization.service.ProductService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + " doesn't find"));
        ;
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByDiscription(String discription) {
        return productRepository.findByDescription(discription);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByOrganizationAndStatusProduct(Organization organization, ProductStatus productStatus) {
        return productRepository.findByOrganizationAndProductStatus(organization, productStatus);
    }

    @Override
    public List<Product> findByOrganization(Organization organization) {
        return productRepository.findByOrganization(organization);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        Product forUpdate = productRepository.findById(id).orElseThrow();
        forUpdate.setName(product.getName());
        forUpdate.setDescription(product.getDescription());
        forUpdate.setPrice(product.getPrice());
        forUpdate.setOrganization(product.getOrganization());
        forUpdate.setProductStatus(product.getProductStatus());
        forUpdate.setCreatedAt(product.getCreatedAt());
        forUpdate.setUpdatedAt(product.getUpdatedAt());
        return productRepository.save(forUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
