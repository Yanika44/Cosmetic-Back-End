package com.example.cosmetic2.service;

import com.example.cosmetic2.model.Color;
import com.example.cosmetic2.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    ColorRepository colorRepository;

    public List<Color> getAllColor(){
        return colorRepository.findAll();
    }

    public void deleteColor(String id){
        colorRepository.deleteById(id);
    }
}
