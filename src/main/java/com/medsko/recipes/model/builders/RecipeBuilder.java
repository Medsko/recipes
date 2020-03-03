package com.medsko.recipes.model.builders;

import com.medsko.recipes.model.Difficulty;
import com.medsko.recipes.model.DirectionStep;
import com.medsko.recipes.model.Ingredient;
import com.medsko.recipes.model.Notes;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.model.UnitOfMeasure;
import org.springframework.util.comparator.Comparators;

import java.math.BigDecimal;

/**
 * One-shot builder for new {@link Recipe}s.
 */
public class RecipeBuilder {

	public static final int DEFAULT_SERVINGS = 4;

	private int stepNumber;

	private Recipe recipe;

	public RecipeBuilder() {
		recipe = new Recipe();
	}

	public Recipe build() {
		Recipe result = recipe;
		// Discourage reuse.
		recipe = null;
		return result;
	}

	public RecipeBuilder magicFill(String description, Difficulty difficulty, Integer prepTime, Integer cookTime, String source, String url) {
		recipe.setDescription(description);
		recipe.setDifficulty(difficulty);
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		recipe.setServings(DEFAULT_SERVINGS);
		recipe.setSource(source);
		recipe.setUrl(url);
		return this;
	}

	public RecipeBuilder withIngredient(BigDecimal amount, UnitOfMeasure uom, String description) {
		Ingredient ingredient = new Ingredient();
		ingredient.setAmount(amount);
		ingredient.setUnitOfMeasure(uom);
		ingredient.setDescription(description);
		recipe.getIngredients().add(ingredient);
		return this;
	}

	public RecipeBuilder withDirectionStep(String description) {
		DirectionStep step = new DirectionStep();
		step.setStepNumber(++stepNumber);
		step.setDescription(description);
		recipe.getDirectionSteps().add(step);
		return this;
	}

	public RecipeBuilder withNotes(String recipeNotes) {
		Notes notes = new Notes();
		notes.setRecipeNotes(recipeNotes);
		recipe.setNotes(notes);
		return this;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		this.stepNumber = recipe.getDirectionSteps().stream()
				.map(DirectionStep::getStepNumber)
				.max(Comparators.comparable())
				.orElse(0);
	}
}
