package recipes.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;
import recipes.utils.TestUtils;

/**
 * Unit test for {@link recipes.mapper.RecipeMapper}
 *
 * @author wabel
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeMapperTest {

    private final RecipeMapper target = new RecipeMapper();

    @Test
    public void testMappingToEntity() {

        RecipeDTO dto = TestUtils.createDTO();
        Recipe result = target.mapToEntity(dto);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(),dto.getName());
        Assert.assertEquals(result.getCookingInstructions(),dto.getCookingInstructions());
        Assert.assertEquals(result.getIngredients().size(),dto.getIngredients().size());
        Assert.assertEquals(result.getCreationDate(),dto.getCreationDate());
        Assert.assertEquals(result.getId(),dto.getId());
        Assert.assertEquals(result.getServesNoPeople(),dto.getServesNoPeople());
        Assert.assertEquals(result.isVegetarian(),dto.isVegetarian());

    }

    @Test
    public void testMappingToDTO() {
        Recipe entity = TestUtils.createEntity();
        RecipeDTO result = target.mapToDTO(entity);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(),entity.getName());
        Assert.assertEquals(result.getCookingInstructions(),entity.getCookingInstructions());
        Assert.assertEquals(result.getIngredients().size(),entity.getIngredients().size());
        Assert.assertEquals(result.getCreationDate(),entity.getCreationDate());
        Assert.assertEquals(result.getId(),entity.getId());
        Assert.assertEquals(result.getServesNoPeople(),entity.getServesNoPeople());
        Assert.assertEquals(result.isVegetarian(),entity.isVegetarian());
    }

}
