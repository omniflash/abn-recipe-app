package recipes.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import recipes.dto.RecipeDTO;
import recipes.service.RecipeService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller class for recipe related operations - This controller is used for the calls inside the application. Uses form data instead of JSON.
 *
 * @author wabel
 */
@Controller
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    RecipeService recipeService;

    // Navigation

    @Operation(summary = "Show index page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @GetMapping("/index")
    public String showRecipes(Model model) {

        LOGGER.info("Showing recipes");
        model.addAttribute("recipes", recipeService.findAllRecipes());
        return "recipes";
    }

    @Operation(summary = "Show add new recipe page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @GetMapping("/addnewrecipe")
    public String showAddNewRecipePage(Model model) {
        LOGGER.info("Showing new recipe page");
        model.addAttribute("recipe", new RecipeDTO());
        return "add-recipe";
    }

    @Operation(summary = "Show edit page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @GetMapping("/editrecipe/{id}")
    public String showEditRecipePage(@PathVariable("id") Integer id, Model model) {
        RecipeDTO recipe = recipeService.findRecipeById(id);

        if (recipe == null) {
            new IllegalArgumentException("Invalid recipe Id:" + id);
        }

        model.addAttribute("recipe", recipe);
        LOGGER.info("Showing edit page for recipe with id {}", id);
        return "edit-recipe";
    }

    // Operations
    @Operation(summary = "Save a new recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe saved",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @PostMapping("/saverecipe")
    public String saveNewRecipe(RecipeDTO recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }
        recipe.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        recipeService.saveRecipe(recipe);
        LOGGER.info("Saving new recipe with name: {}", recipe.getName());
        return "redirect:/index";
    }

    @Operation(summary = "Update recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe updated",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @PostMapping("/editrecipe/{id}")
    public String updateRecipe(@PathVariable("id") Integer id, RecipeDTO recipe,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            recipe.setId(id);
            return "edit-recipe";
        }
        LOGGER.info("Updating recipe with id {}", id);
        recipeService.saveRecipe(recipe);
        return "redirect:/index";
    }

    @Operation(summary = "Delete recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("Deleting recipe with id {}", id);
        recipeService.deleteRecipe(id);
        return "redirect:/index";
    }

    @Operation(summary = "Delete recipe ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized!",
                    content = @Content)
    })
    @GetMapping("/deleteIngredient/{id}/{recipeId}")
    public String deleteIngredient(@PathVariable("id") Integer id, @PathVariable("recipeId") Integer recipeId, Model model) {
        LOGGER.info("Deleting ingredient no. {} recipe with id {}", recipeId, id);
        RecipeDTO recipeById = recipeService.findRecipeById(id);
        List<String> ingredients = new ArrayList<>(recipeById.getIngredients());
        ingredients.remove(recipeId.intValue());
        recipeById.setIngredients(ingredients);
        recipeService.saveRecipe(recipeById);
        model.addAttribute("recipe", recipeById);
        return "edit-recipe";
    }

}
