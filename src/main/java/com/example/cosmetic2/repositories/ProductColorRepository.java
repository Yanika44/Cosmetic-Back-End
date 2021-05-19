package com.example.cosmetic2.repositories;

import com.example.cosmetic2.model.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor,String> {
    @Query(value = "DELETE FROM product_color WHERE productid = ?1", nativeQuery = true)
    void deleteProductColorByProductId(String productId);
}
