package com.example.demojava.controllers;

import com.example.demojava.dto.ProductRequest;
import com.example.demojava.models.ApiResponse;
import com.example.demojava.models.Product;
import com.example.demojava.services.ProductService;
import jakarta.validation.Valid;
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
    public ApiResponse<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(Double.parseDouble(request.getPrice()));
        return new ApiResponse<>("success", productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> update (@Valid @RequestBody Product product, @PathVariable Long id) {
        Product updatedProduct = productService.updateProduct(id, product);
        return  new ApiResponse<>("success", updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete (@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ApiResponse<>("success", "Data ID "+ id +" Deleted");
    }

}
