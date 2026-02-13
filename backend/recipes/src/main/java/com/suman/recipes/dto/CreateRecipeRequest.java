package com.suman.recipes.dto;

public record CreateRecipeRequest(
        String name,
        Integer prepTimeMinutes,
        Integer cookTimeMinutes,
        Integer servings,
        String difficulty,
        String cuisine,
        Integer caloriesPerServing,
        String image
) {}
