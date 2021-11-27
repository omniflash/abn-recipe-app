package recipes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeDTO;
import recipes.service.RecipeService;

import java.util.List;

/**
 * Controller class for recipe related operations - accepts JSON. This controller handles REST calls from outside the application (eg. Postman, curl)
 *
 * @author wabel
 */
@RestController
@RequestMapping(value = "/external")
public class ExternalRecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalRecipeController.class);

    @Autowired
    RecipeService recipeService;

    @Operation(summary = "Return all recipes as JSON")
    @GetMapping("/getallrecipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of recipes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden! - Admin role needed",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Method not allowed.",
                    content = @Content)

    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public List<RecipeDTO> showRecipes() {
        LOGGER.info("(External call) Fetching all recipes");
        return recipeService.findAllRecipes();
    }

    @Operation(summary = "Save a new recipe - JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe saved",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden! - Admin role needed",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Method not allowed.",
                    content = @Content)
    })
    @PostMapping("/saverecipe")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String saveNewRecipeJSON(@RequestBody RecipeDTO recipe) {
        LOGGER.info("(External call) Saving new recipe with name: {}", recipe.getName());
        recipeService.saveRecipe(recipe);
        return HttpStatus.OK.toString();
    }

    @Operation(summary = "Save multiple new recipes - JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes saved",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden! - Admin role needed",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Method not allowed.",
                    content = @Content)
    })
    @PostMapping("/saverecipes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String saveNewRecipesJSON(@RequestBody List<RecipeDTO> recipes) {
        LOGGER.info("(External call) Saving {} new recipes", recipes.size());
        recipeService.saveRecipes(recipes);
        return HttpStatus.OK.toString();
    }

    @Operation(summary = "Update recipe - JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe updated",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden! - Admin role needed",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Method not allowed.",
                    content = @Content)
    })
    @PutMapping("/editrecipe/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String updateRecipe(@PathVariable("id") Integer id, @RequestBody RecipeDTO recipe) {
        recipe.setId(id);
        LOGGER.info("(External call) Updating recipe with id: {}", id);
        recipeService.saveRecipe(recipe);
        return HttpStatus.OK.toString();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden! - Admin role needed",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Method not allowed.",
                    content = @Content)
    })
    @Operation(summary = "Delete recipe - JSON")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteRecipe(@PathVariable("id") Integer id) {
        LOGGER.info("(External call) Deleting recipe with id: {}", id);
        recipeService.deleteRecipe(id);
        return HttpStatus.OK.toString();
    }

}
