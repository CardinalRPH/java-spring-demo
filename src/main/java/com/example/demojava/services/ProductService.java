package com.example.demojava.services;

import com.example.demojava.models.Product;
import com.example.demojava.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
   private final ProductRepository productRepository;

   public ProductService(ProductRepository productRepository) {
       this.productRepository = productRepository;
   }

   public  List<Product> getAllProduct(){
       return productRepository.findAll();
   }

   public Product addProduct(Product product) {
       return productRepository.save(product);
   }

   public Product updateProduct(Long id, Product detailProduct) {
       Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

       product.setName(detailProduct.getName());
       product.setPrice(detailProduct.getPrice());
       return productRepository.save(product);
   }

   public void deleteProduct(Long id) {
       Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
       productRepository.delete(product);
   }
}
