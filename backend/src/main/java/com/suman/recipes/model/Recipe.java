package com.suman.recipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;

    private String difficulty;
    private String cuisine;

    private Integer caloriesPerServing;

    private Double rating;
    private Integer reviewCount;

    private String image;
}
