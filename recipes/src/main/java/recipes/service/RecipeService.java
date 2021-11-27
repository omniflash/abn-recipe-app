package recipes.service;

import recipes.dto.RecipeDTO;

import java.util.List;

/**
 * Business service for recipe related operations
 *
 * @author wabel
 */
public interface RecipeService {

    RecipeDTO findRecipeById(Integer id);

    void saveRecipe(RecipeDTO recipe);

    void deleteRecipe(Integer id);

    List<RecipeDTO> findAllRecipes();

    void saveRecipes(List<RecipeDTO> recipes);

    void patchRecipe(RecipeDTO recipe, Integer id);
}
