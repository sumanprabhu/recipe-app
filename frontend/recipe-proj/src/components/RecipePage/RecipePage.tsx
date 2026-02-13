import { useEffect, useState } from "react";
import "./RecipePage.css";
import type { Recipe, ApiResponse } from "../../types";
import RecipeCard from "../RecipeCard/RecipeCard";
import RecipeModal from "../RecipeModal/RecipeModal";
import axios from "axios";

export default function RecipePage() {
  const [recipes, setRecipes] = useState<Recipe[]>([]);
  const [selectedRecipe, setSelectedRecipe] = useState<Recipe | null>(null);

  const fetchRecipes = async () => {
    const { data } = await axios.get<ApiResponse>(
      "https://dummyjson.com/recipes",
    );
    setRecipes(data.recipes);
  };

  async function searchRecipes(query: string) {
    if (!query) {
      fetchRecipes();
      return;
    }
    const { data } = await axios.get<ApiResponse>(
      `https://dummyjson.com/recipes/search?q=${query}`,
    );
    setRecipes(data.recipes);
  }

  useEffect(() => {
    fetchRecipes();
  }, []);

  let debounceTimer: number;

  function debounceSearch(query: string) {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
      searchRecipes(query);
    }, 300);
  }

  return (
    <div>
      <h1 className="heading-one">Welcome to Recipes App</h1>
      <p className="recipe-count">Total recipes: {recipes.length}</p>

      <div>
        <input
          type="text"
          id="searchDish"
          name="searchDish"
          placeholder="Search Dishes..."
          onChange={(e) => debounceSearch(e.target.value)}
          className="search-input"
        />

        <div className="recipes-wrapper">
          <div className="recipes-container">
            {recipes.map((recipe) => (
              <RecipeCard
                key={recipe.id}
                recipe={recipe}
                onSelect={(r) => setSelectedRecipe(r)}
              />
            ))}
          </div>
        </div>
      </div>

      {selectedRecipe && (
        <RecipeModal
          recipe={selectedRecipe}
          onClose={() => setSelectedRecipe(null)}
        />
      )}
    </div>
  );
}
