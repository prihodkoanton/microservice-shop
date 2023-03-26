package ru.effectivemobile.microserviceorder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.effectivemobile.microserviceorder.core.dto.ProductDto;
import ru.effectivemobile.microserviceorder.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/v1/products";

    private ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> products = productService.getAll().stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<ProductDto> getById(@RequestParam ("id") Long id){
        ProductDto productDto = ProductDto.toDto(productService.getById(id));
        return ResponseEntity.ok(productDto);
    }

    public ResponseEntity<List<ProductDto>> getByName(@RequestParam ("name") String name){
        List<ProductDto> products = productService.getByName(name).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<ProductDto>> getByDescription(@RequestParam ("description") String description){
        List<ProductDto> products = productService.getByDescription(description).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<ProductDto>> getByPrice(@RequestParam ("price") BigDecimal price){
        List<ProductDto> products = productService.getByPrice(price).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<ProductDto>> findByCreatedBetween(@RequestParam("start") ){
        List<ProductDto> products = productService.getByPrice(price).stream().map(product -> ProductDto.toDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

}
