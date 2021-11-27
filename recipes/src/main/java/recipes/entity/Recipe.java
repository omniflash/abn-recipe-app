package recipes.entity;

import recipes.converter.StringListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entity for recipes
 *
 * @author wabel
 */
@Entity
@Table(name="RECIPES")
public class Recipe {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Convert(converter = StringListConverter.class)
    private List<String> ingredients;
    private String cookingInstructions;
    private boolean vegetarian;
    private int servesNoPeople;
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

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
        ingredients.remove(index);
    }
}
