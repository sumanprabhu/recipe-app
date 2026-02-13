package com.suman.recipes.dto;

import java.util.UUID;

public record RecipeResponse(
        UUID id,
        String name,
        Integer prepTimeMinutes,
        Integer cookTimeMinutes,
        Integer servings,
        String difficulty,
        String cuisine,
        Integer caloriesPerServing,
        String image
) {}
