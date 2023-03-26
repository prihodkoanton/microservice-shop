package ru.effectivemobile.microserviceorder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceorder.core.model.Product;
import ru.effectivemobile.microserviceorder.repository.ProductRepository;
import ru.effectivemobile.microserviceorder.service.ProductService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(Long id) {

        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    @Override
    public List<Product> getByPrice(BigDecimal price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> getByCreatedBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return productRepository.findByCreatedBetween(startDate, endDate);
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        Product forUpdate = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product for update with id: " + id + " doesn't find"));
        forUpdate.setName(product.getName());
        forUpdate.setDescription(product.getDescription());
        forUpdate.setPrice(product.getPrice());
        forUpdate.setCreated(product.getCreated());
        forUpdate.setUpdated(product.getUpdated());
        return productRepository.save(product);
    }
}
