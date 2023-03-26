package ru.effectivemobile.microserviceorder.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.microserviceorder.core.dto.ProductDto;
import ru.effectivemobile.microserviceorder.core.model.Product;
import ru.effectivemobile.microserviceorder.service.ProductService;

import javax.validation.Valid;
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
        List<ProductDto> productDtos = productService.getAll().stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        ProductDto productDto = ProductDto.toDto(productService.getById(id));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDto>> getByName(@PathVariable("name") String name) {
        List<ProductDto> productDtos = productService.getByName(name).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<ProductDto>> getByDescription(@PathVariable("description") String description) {
        List<ProductDto> productDtos = productService.getByDescription(description).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDto>> getByPrice(@PathVariable("price") BigDecimal price) {
        List<ProductDto> productDtos = productService.getByPrice(price).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/time/{start}/{end}")
    public ResponseEntity<List<ProductDto>> findByCreatedBetween(@PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
                                                                 @PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        List<ProductDto> productDtos = productService.getByCreatedBetween(startDate, endDate).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable ("id") Long id, @Valid @RequestBody ProductDto dto){
        Product formDto = ProductDto.toProduct(dto);
        ProductDto result =  ProductDto.toDto(productService.update(id, formDto));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto){
        ProductDto result = ProductDto.toDto(productService.create(ProductDto.toProduct(dto)));
        return  ResponseEntity.ok(result);
    }

}
