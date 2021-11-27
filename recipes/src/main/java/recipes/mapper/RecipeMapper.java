package recipes.mapper;

import org.springframework.stereotype.Service;
import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;

import java.util.Date;

/**
 * Mapper class between {@link Recipe} and {@link RecipeDTO}
 *
 * @author wabel
 */
@Service
public class RecipeMapper {

    public RecipeDTO mapToDTO(Recipe entity) {

        final RecipeDTO result = new RecipeDTO();
        result.setCreationDate(entity.getCreationDate());
        result.setIngredients(entity.getIngredients());
        result.setCookingInstructions(entity.getCookingInstructions());
        result.setServesNoPeople(entity.getServesNoPeople());
        result.setVegetarian(entity.isVegetarian());
        result.setName(entity.getName());
        result.setId(entity.getId());

        return result;

    }

    public Recipe mapToEntity(RecipeDTO dto) {

        final Recipe result = new Recipe();
        result.setCreationDate(dto.getCreationDate() == null ? new Date() : dto.getCreationDate());
        result.setIngredients(dto.getIngredients());
        result.setCookingInstructions(dto.getCookingInstructions());
        result.setServesNoPeople(dto.getServesNoPeople() == null ? 0 : dto.getServesNoPeople());
        result.setVegetarian(dto.getVegetarian());
        result.setName(dto.getName());
        result.setId(dto.getId());

        return result;
    }

    public Recipe mapToEntityForPatch(RecipeDTO dto, Recipe recipe) {

        recipe.setCreationDate(new Date());

        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            recipe.setIngredients(dto.getIngredients());
        }

        if (dto.getCookingInstructions() != null) {
            recipe.setCookingInstructions(dto.getCookingInstructions());
        }

        if (dto.getServesNoPeople() != null) {
            recipe.setServesNoPeople(dto.getServesNoPeople());
        }

        if (dto.getVegetarianObject() != null) {
            recipe.setVegetarian(dto.getVegetarianObject().booleanValue());
        }

        if (dto.getName() != null) {
            recipe.setName(dto.getName());
        }

        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            recipe.setIngredients(dto.getIngredients());
        }

        return recipe;
    }

}
