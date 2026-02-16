package com.suman.recipes.service;

import com.suman.recipes.dto.RecipeResponse;
import com.suman.recipes.dto.UpdateRecipeRequest;
import com.suman.recipes.model.Recipe;
import com.suman.recipes.dto.CreateRecipeRequest;
import com.suman.recipes.repo.RecipeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public RecipeResponse createRecipe(CreateRecipeRequest request) {

        Recipe recipe = new Recipe();

        recipe.setName(request.name());
        recipe.setPrepTimeMinutes(request.prepTimeMinutes());
        recipe.setCookTimeMinutes(request.cookTimeMinutes());
        recipe.setServings(request.servings());
        recipe.setDifficulty(request.difficulty());
        recipe.setCuisine(request.cuisine());
        recipe.setCaloriesPerServing(request.caloriesPerServing());
        recipe.setImage(request.image());

        Recipe saved = recipeRepo.save(recipe);

        return RecipeResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .prepTimeMinutes(saved.getPrepTimeMinutes())
                .cookTimeMinutes(saved.getCookTimeMinutes())
                .servings(saved.getServings())
                .difficulty(saved.getDifficulty())
                .cuisine(saved.getCuisine())
                .caloriesPerServing(saved.getCaloriesPerServing())
                .image(saved.getImage())
                .build();
    }

    public RecipeResponse getRecipe(UUID id) {

        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Recipe does not exist with the specified id"
                        )
                );

        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .prepTimeMinutes(recipe.getPrepTimeMinutes())
                .cookTimeMinutes(recipe.getCookTimeMinutes())
                .servings(recipe.getServings())
                .difficulty(recipe.getDifficulty())
                .cuisine(recipe.getCuisine())
                .caloriesPerServing(recipe.getCaloriesPerServing())
                .image(recipe.getImage())
                .build();
    }

    public List<RecipeResponse> getRecipes(
            String name,
            String cuisine,
            String difficulty
    ) {

        List<Recipe> recipes;

        if (name != null && cuisine != null && difficulty != null) {
            recipes = recipeRepo
                    .findByNameContainingIgnoreCaseAndCuisineIgnoreCaseAndDifficultyIgnoreCase(
                            name, cuisine, difficulty);

        } else if (name != null && cuisine != null) {
            recipes = recipeRepo
                    .findByNameContainingIgnoreCaseAndCuisineIgnoreCase(
                            name, cuisine);

        } else if (name != null && difficulty != null) {
            recipes = recipeRepo
                    .findByNameContainingIgnoreCaseAndDifficultyIgnoreCase(
                            name, difficulty);

        } else if (cuisine != null && difficulty != null) {
            recipes = recipeRepo
                    .findByCuisineIgnoreCaseAndDifficultyIgnoreCase(
                            cuisine, difficulty);

        } else if (name != null) {
            recipes = recipeRepo.findByNameContainingIgnoreCase(name);

        } else if (cuisine != null) {
            recipes = recipeRepo.findByCuisineIgnoreCase(cuisine);

        } else if (difficulty != null) {
            recipes = recipeRepo.findByDifficultyIgnoreCase(difficulty);

        } else {
            recipes = recipeRepo.findAll();
        }

        return recipes.stream()
                .map(r -> new RecipeResponse(
                        r.getId(),
                        r.getName(),
                        r.getPrepTimeMinutes(),
                        r.getCookTimeMinutes(),
                        r.getServings(),
                        r.getDifficulty(),
                        r.getCuisine(),
                        r.getCaloriesPerServing(),
                        r.getImage()
                ))
                .toList();
    }

    public RecipeResponse updateRecipe(UUID id, UpdateRecipeRequest request) {

        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Recipe not found")
                );

        recipe.setName(request.name());
        recipe.setPrepTimeMinutes(request.prepTimeMinutes());
        recipe.setCookTimeMinutes(request.cookTimeMinutes());
        recipe.setServings(request.servings());
        recipe.setDifficulty(request.difficulty());
        recipe.setCuisine(request.cuisine());
        recipe.setCaloriesPerServing(request.caloriesPerServing());
        recipe.setImage(request.image());

        Recipe updated = recipeRepo.save(recipe);

        return RecipeResponse.builder()
                .id(updated.getId())
                .name(updated.getName())
                .prepTimeMinutes(updated.getPrepTimeMinutes())
                .cookTimeMinutes(updated.getCookTimeMinutes())
                .servings(updated.getServings())
                .difficulty(updated.getDifficulty())
                .cuisine(updated.getCuisine())
                .caloriesPerServing(updated.getCaloriesPerServing())
                .image(updated.getImage())
                .build();
    }

    public void deleteRecipe(UUID id){
        recipeRepo.deleteById(id);
    }
}