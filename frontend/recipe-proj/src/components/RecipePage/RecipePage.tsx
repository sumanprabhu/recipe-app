import { useEffect, useState } from "react";
import "./RecipePage.css";
import type { Recipe } from "../../types";
import RecipeCard from "../RecipeCard/RecipeCard";
import RecipeModal from "../RecipeModal/RecipeModal";
import axios from "axios";

export default function RecipePage() {
  const [recipes, setRecipes] = useState<Recipe[]>([]);
  const [selectedRecipe, setSelectedRecipe] = useState<Recipe | null>(null);
  const [search, setSearch] = useState("");
  const [cuisine, setCuisine] = useState("");
  const [difficulty, setDifficulty] = useState("");

  const fetchRecipes = async (
    name = search,
    selectedCuisine = cuisine,
    selectedDifficulty = difficulty,
  ) => {
    const params = new URLSearchParams();

    if (name) params.append("name", name);
    if (selectedCuisine) params.append("cuisine", selectedCuisine);
    if (selectedDifficulty) params.append("difficulty", selectedDifficulty);

    const { data } = await axios.get<Recipe[]>(
      `http://localhost:8080/recipes?${params.toString()}`,
    );

    setRecipes(data);
  };

  useEffect(() => {
    fetchRecipes();
  }, []);

  let debounceTimer: number;

  function debounceSearch(query: string) {
    clearTimeout(debounceTimer);

    debounceTimer = window.setTimeout(() => {
      fetchRecipes(query, cuisine, difficulty);
    }, 300);
  }

  return (
    <div>
      <h1 className="heading-one">Welcome to Recipes App</h1>
      <p className="recipe-count">Total recipes: {recipes.length}</p>

      <div className="filters-container">
        <input
          type="text"
          placeholder="Search Dishes..."
          value={search}
          onChange={(e) => {
            setSearch(e.target.value);
            debounceSearch(e.target.value);
            fetchRecipes(e.target.value, cuisine, difficulty);
          }}
        />
        <select
          value={cuisine}
          onChange={(e) => {
            setCuisine(e.target.value);
            fetchRecipes(search, e.target.value, difficulty);
          }}
        >
          <option value="">All Cuisines</option>
          <option value="Indian">Indian</option>
          <option value="Italian">Italian</option>
          <option value="Chinese">Chinese</option>
          <option value="American">American</option>
        </select>

        <select
          value={difficulty}
          onChange={(e) => {
            setDifficulty(e.target.value);
            fetchRecipes(search, cuisine, e.target.value);
          }}
        >
          <option value="">All Difficulties</option>
          <option value="Easy">Easy</option>
          <option value="Medium">Medium</option>
          <option value="Hard">Hard</option>
        </select>

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
