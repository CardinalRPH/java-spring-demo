package com.example.demojava.services;

import com.example.demojava.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public  ProductService() {
        products.add(new Product(1L,"Lontong Balap", 12000000));
        products.add(new Product(2L,"Lontong Kasarung", 3000000));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        products.add(product);
        return  product;
    }
}
