package ru.effectivemobile.microserviceorder.core.exceptions;

public class ProductNotFoundException  extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
