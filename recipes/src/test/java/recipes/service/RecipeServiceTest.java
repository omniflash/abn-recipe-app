package recipes.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import recipes.dto.RecipeDTO;
import recipes.entity.Recipe;
import recipes.mapper.RecipeMapper;
import recipes.repository.RecipeRepository;
import recipes.service.impl.RecipeServiceImpl;

import java.util.Optional;

/**
 * Unit test for {@link RecipeService}
 *
 * @author wabel
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    private  static final int ID = 1;

    @Mock
    private RecipeRepository repositoryMock;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    @Autowired
    private RecipeServiceImpl target;


    @Test
    public void testSave() {

        target.saveRecipe(new RecipeDTO());
        Mockito.verify(repositoryMock).save(Mockito.any());

    }

    @Test
    public void testDelete() {
        Optional<Recipe> recipe = Optional.of(new Recipe());
        Mockito.when(repositoryMock.findById(ID)).thenReturn(recipe);
        target.deleteRecipe(ID);
        Mockito.verify(repositoryMock).delete(Mockito.any());
    }

    @Test
    public void testFindById() {

        Recipe entity = new Recipe();
        entity.setId(ID);
        Optional<Recipe> recipe = Optional.of(entity);
        Mockito.when(repositoryMock.findById(ID)).thenReturn(recipe);

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(ID);
        Mockito.when(recipeMapper.mapToDTO(Mockito.any())).thenReturn(recipeDTO);

        RecipeDTO result = target.findRecipeById(ID);
        Mockito.verify(repositoryMock).findById(ID);

        Assert.assertNotNull(result);
        Assert.assertEquals(ID, result.getId().intValue());
    }

}
