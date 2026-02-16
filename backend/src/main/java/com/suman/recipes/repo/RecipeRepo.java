package com.suman.recipes.repo;

import com.suman.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeRepo extends JpaRepository<Recipe, UUID> {
    List<Recipe> findByNameContainingIgnoreCase(String name);

    List<Recipe> findByCuisineIgnoreCase(String cuisine);

    List<Recipe> findByDifficultyIgnoreCase(String difficulty);

    List<Recipe> findByNameContainingIgnoreCaseAndCuisineIgnoreCase(
            String name, String cuisine
    );

    List<Recipe> findByNameContainingIgnoreCaseAndDifficultyIgnoreCase(
            String name, String difficulty
    );

    List<Recipe> findByCuisineIgnoreCaseAndDifficultyIgnoreCase(
            String cuisine, String difficulty
    );

    List<Recipe> findByNameContainingIgnoreCaseAndCuisineIgnoreCaseAndDifficultyIgnoreCase(
            String name, String cuisine, String difficulty
    );
}
