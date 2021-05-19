package com.example.cosmetic2.repositories;

import com.example.cosmetic2.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,String> {
}
