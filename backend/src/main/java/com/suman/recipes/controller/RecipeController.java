package com.suman.recipes.controller;

import com.suman.recipes.dto.CreateRecipeRequest;
import com.suman.recipes.dto.RecipeResponse;
import com.suman.recipes.dto.UpdateRecipeRequest;
import com.suman.recipes.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeResponse> getRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String difficulty
    ) {
        return recipeService.getRecipes(name, cuisine, difficulty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable UUID id){
        return new ResponseEntity<>(recipeService.getRecipe(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody CreateRecipeRequest request){
        return new ResponseEntity<>(recipeService.createRecipe(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public RecipeResponse updateRecipe(
            @PathVariable UUID id,
            @RequestBody UpdateRecipeRequest request
    ) {
        return recipeService.updateRecipe(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable UUID id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok("Deleted recipe with the specified id");
    }
}
