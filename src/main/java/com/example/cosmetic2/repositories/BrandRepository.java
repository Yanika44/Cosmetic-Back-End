package com.example.cosmetic2.repositories;

import com.example.cosmetic2.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,String> {
}
