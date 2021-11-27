package recipes.utils;

import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;

import java.util.Arrays;
import java.util.Date;

/**
 * Test utility class
 *
 * @author wabel
 */
public class TestUtils {

    private TestUtils() {

    }

    public static RecipeDTO createDTO() {
        final RecipeDTO result = new RecipeDTO();
        result.setIngredients(Arrays.asList("Sugar","Salt","EverythingNice"));
        result.setId(1);
        result.setCreationDate(new Date());
        result.setName("ChemicalX");
        result.setVegetarian(true);
        result.setServesNoPeople(3);
        result.setCookingInstructions("Mix together");
        return result;
    }

    public static Recipe createEntity() {
        final Recipe result = new Recipe();
        result.setIngredients(Arrays.asList("Sugar","Salt","EverythingNice"));
        result.setId(1);
        result.setCreationDate(new Date());
        result.setName("ChemicalX");
        result.setVegetarian(true);
        result.setServesNoPeople(3);
        result.setCookingInstructions("Mix together");
        return result;
    }


}
