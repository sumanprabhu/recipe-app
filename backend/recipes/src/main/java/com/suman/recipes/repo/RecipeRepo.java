package com.suman.recipes.repo;

import com.suman.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepo extends JpaRepository<Recipe, UUID> {
}
