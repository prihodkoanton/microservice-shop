package ru.effectivemobile.microserviceorder.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.effectivemobile.microserviceorder.core.dto.ProductDto;
import ru.effectivemobile.microserviceorder.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/v1/products";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> products = productService.getAll().stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@RequestParam("id") Long id) {
        ProductDto productDto = ProductDto.toDto(productService.getById(id));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductDto>> getByName(@RequestParam("name") String name) {
        List<ProductDto> products = productService.getByName(name).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{description}")
    public ResponseEntity<List<ProductDto>> getByDescription(@RequestParam("description") String description) {
        List<ProductDto> products = productService.getByDescription(description).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{price}")
    public ResponseEntity<List<ProductDto>> getByPrice(@RequestParam("price") BigDecimal price) {
        List<ProductDto> products = productService.getByPrice(price).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<ProductDto>> findByCreatedBetween(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
                                                                 @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        List<ProductDto> products = productService.getByCreatedBetween(startDate, endDate).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

}
