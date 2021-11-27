package recipes.mapper;

import org.springframework.stereotype.Service;
import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;

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
        result.setCreationDate(dto.getCreationDate());
        result.setIngredients(dto.getIngredients());
        result.setCookingInstructions(dto.getCookingInstructions());
        result.setServesNoPeople(dto.getServesNoPeople());
        result.setVegetarian(dto.isVegetarian());
        result.setName(dto.getName());
        result.setId(dto.getId());

        return result;
    }

}
