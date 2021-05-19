package com.example.cosmetic2.controller;

import com.example.cosmetic2.model.Color;
import com.example.cosmetic2.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/color")
    public List<Color> getAllColor(){
        return colorService.getAllColor();
    }

//    @DeleteMapping("/color/{id}")
//    public void deleteColor(@PathVariable String id){
//        colorService.deleteColor(id);
//    }
}
