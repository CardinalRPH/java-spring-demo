package com.example.demojava.controllers;

import com.example.demojava.models.ApiResponse;
import com.example.demojava.models.Product;
import com.example.demojava.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        List<Product> products = productService.getAllProduct();
        return new ApiResponse<>("success", products);
    }

    @PostMapping
    public ApiResponse<Product> create(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ApiResponse<>("success", newProduct);
    }

}
