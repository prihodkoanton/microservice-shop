package ru.effectivemobile.microserviceorder.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.microserviceorder.core.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime created;

    private LocalDateTime updated;

    public static Product toProduct(ProductDto dto){
        ru.effectivemobile.microserviceorder.core.model.Product fromDto = new ru.effectivemobile.microserviceorder.core.model.Product();
        fromDto.setName(dto.getName());
        fromDto.setDescription(dto.getDescription());
        fromDto.setPrice(dto.getPrice());
        fromDto.setCreated(dto.getCreated());
        fromDto.setUpdated(dto.getUpdated());
        return  fromDto;
    }

    public static ProductDto toDto (ru.effectivemobile.microserviceorder.core.model.Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCreated(product.getCreated());
        productDto.setUpdated(product.getUpdated());
        return  productDto;
    }
}
