export interface Recipe {
  id: string;
  name: string;
  image: string;
  cuisine: string;
  difficulty: string;
  prepTimeMinutes: number;
  cookTimeMinutes: number;
  servings: number;
}
