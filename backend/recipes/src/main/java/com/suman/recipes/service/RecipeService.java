package com.suman.recipes.service;

import com.suman.recipes.dto.RecipeResponse;
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

        return new RecipeResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrepTimeMinutes(),
                saved.getCookTimeMinutes(),
                saved.getServings(),
                saved.getDifficulty(),
                saved.getCuisine(),
                saved.getCaloriesPerServing(),
                saved.getImage()
        );
    }

    public void deleteRecipe(UUID id){
        recipeRepo.deleteById(id);
    }

    public Recipe getRecipe(UUID id) {
        return recipeRepo.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Recipe does not exist with the specified id")
        );
    }

    public List<RecipeResponse> getRecipes() {
        return recipeRepo.findAll()
                .stream()
                .map(recipe -> new RecipeResponse(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getPrepTimeMinutes(),
                        recipe.getCookTimeMinutes(),
                        recipe.getServings(),
                        recipe.getDifficulty(),
                        recipe.getCuisine(),
                        recipe.getCaloriesPerServing(),
                        recipe.getImage()
                ))
                .toList();
    }
}