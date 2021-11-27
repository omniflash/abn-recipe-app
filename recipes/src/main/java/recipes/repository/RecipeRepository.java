package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.Recipe;

/**
 * Repository for {@link Recipe} entities
 *
 * @author wabel
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
