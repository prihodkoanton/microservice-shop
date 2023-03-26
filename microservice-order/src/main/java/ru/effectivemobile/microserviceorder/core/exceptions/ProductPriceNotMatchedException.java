package ru.effectivemobile.microserviceorder.core.exceptions;

public class ProductPriceNotMatchedException extends RuntimeException {

    public ProductPriceNotMatchedException(String message) {
        super(message);
    }
}
