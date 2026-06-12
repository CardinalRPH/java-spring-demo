package com.example.demojava.repository;

import com.example.demojava.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Semua fungsi CRUD standard otomatis tersedia di sini
}
