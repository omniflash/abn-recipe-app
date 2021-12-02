package recipes.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;
import recipes.mapper.RecipeMapper;
import recipes.repository.RecipeRepository;
import recipes.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation of {@link RecipeService}
 *
 * @author wabel
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper mapper;

    @Override
    public RecipeDTO findRecipeById(Integer id) {

        LOGGER.debug("Looking for recipe in repository with id: {}", id);
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            return mapper.mapToDTO(result.get());
        } else {
            throw new IllegalArgumentException("Invalid recipe Id:" + id);
        }
    }

    @Override
    public void saveRecipe(RecipeDTO recipe) {
        LOGGER.debug("Saving recipe in repository with name: {}", recipe.getName());
        recipeRepository.save(mapper.mapToEntity(recipe));
    }

    @Override
    public void deleteRecipe(Integer id) {
        LOGGER.debug("Deleting recipe from repository with id: {}", id);
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            recipeRepository.delete(result.get());
        } else {
            throw new IllegalArgumentException("Invalid recipe Id:" + id);
        }
    }

    @Override
    public List<RecipeDTO> findAllRecipes() {
        LOGGER.debug("Fetching all recipes from repository");
        List<Recipe> allRecipes = recipeRepository.findAll();
        return allRecipes == null ? null : allRecipes.stream().map(x -> mapper.mapToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public void saveRecipes(List<RecipeDTO> recipes) {
        recipes.stream().forEach(x -> saveRecipe(x));
    }

    @Override
    public void patchRecipe(RecipeDTO recipe, Integer id) {
        LOGGER.debug("Patching recipe with id: {}", id);
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            Recipe recipeInDb = result.get();
            recipeRepository.save(mapper.mapToEntityForPatch(recipe, recipeInDb));
        } else {
            throw new IllegalArgumentException("Invalid recipe Id:" + id);
        }
    }


}
