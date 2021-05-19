package com.example.cosmetic2.repositories;

import com.example.cosmetic2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
