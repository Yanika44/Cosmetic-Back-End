package com.example.cosmetic2.service;

import com.example.cosmetic2.model.Brand;
import com.example.cosmetic2.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }

    public void deleteBrand(String id){
        brandRepository.deleteById(id);
    }
}
