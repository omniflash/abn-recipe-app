package recipes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for Recipe objects
 *
 * @author wabel
 *
 */
public class RecipeDTO implements Serializable {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd‐MM‐yyyy HH:mm");

    private Integer id;
    private String name;
    private List<String> ingredients;
    private String cookingInstructions;
    private boolean vegetarian;
    private int servesNoPeople;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd‐MM‐yyyy HH:mm")
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getServesNoPeople() {
        return servesNoPeople;
    }

    public void setServesNoPeople(int servesNoPeople) {
        this.servesNoPeople = servesNoPeople;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    // Used on frontend to display
    public String getCreatedString() {
        return creationDate == null ?  DATE_FORMAT.format(new Date()) : DATE_FORMAT.format(creationDate);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    //TODO: finish implementation on frontend

    public String addIngredient(final String ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }

        ingredients.add(ingredient);
        return ingredient;
    }

    public void removeIngredient(final Integer index) {
        if (ingredients == null) {
            return;
        }
        ingredients.remove(index.intValue());
    }

}
