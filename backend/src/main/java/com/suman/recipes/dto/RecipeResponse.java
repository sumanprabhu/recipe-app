package com.suman.recipes.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
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
