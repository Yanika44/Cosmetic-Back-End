package com.example.cosmetic2.service;

import com.example.cosmetic2.model.ProductColor;
import com.example.cosmetic2.repositories.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductColorService {
    @Autowired
    ProductColorRepository productColorRepository;

    public List<ProductColor> getAllProductColor(){
        return productColorRepository.findAll();
    }

    public void deleteProductColor(String id){
        productColorRepository.deleteById(id);
    }

    public void findById(String productId){
        productColorRepository.deleteProductColorByProductId(productId);
    }

    public void deleteProductColorByProductId(String productId){
        productColorRepository.deleteById(productId);
    }
}
