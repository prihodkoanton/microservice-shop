package ru.effectivemobile.microserviceorder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/v1/products";
}
