package com.medsko.recipes.converters;

import com.medsko.recipes.commands.DirectionStepCommand;
import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.commands.LabelCommand;
import com.medsko.recipes.commands.NotesCommand;
import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.model.Difficulty;
import com.medsko.recipes.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeCommandToRecipeTest {

	private static final Long RECIPE_ID = 1L;
	private static final Integer COOK_TIME = Integer.valueOf("5");
	private static final Integer PREP_TIME = Integer.valueOf("7");
	private static final String DESCRIPTION = "My Recipe";
	private static final Difficulty DIFFICULTY = Difficulty.EASY;
	private static final Integer SERVINGS = Integer.valueOf("3");
	private static final String SOURCE = "Source";
	private static final String URL = "Some URL";

	private RecipeCommandToRecipe recipeCommandToRecipe;

	@BeforeEach
	void setUp() {
		recipeCommandToRecipe = new RecipeCommandToRecipe(
				new LabelCommandToLabel(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
				new NotesCommandToNotes(),
				new DirectionStepCommandToDirectionStep()
		);
	}

	@Test
	void testNull() {
		assertNull(recipeCommandToRecipe.convert(null));
	}

	@Test
	void testEmpty() {
		assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
	}

	@Test
	void testConvert() {
		RecipeCommand command = new RecipeCommand();
		command.setId(RECIPE_ID);
		command.setCookTime(COOK_TIME);
		command.setDescription(DESCRIPTION);
		command.setPrepTime(PREP_TIME);
		command.setServings(SERVINGS);
		command.setSource(SOURCE);
		command.setUrl(URL);
		command.setDifficulty(DIFFICULTY);

		command.setNotes(new NotesCommand());
		command.getDirectionSteps().add(new DirectionStepCommand());
		command.getLabels().add(new LabelCommand());
		command.getIngredients().add(new IngredientCommand());

		Recipe recipe = recipeCommandToRecipe.convert(command);

		assertNotNull(recipe);
		assertEquals(RECIPE_ID, recipe.getId());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(DIFFICULTY, recipe.getDifficulty());

		assertNotNull(recipe.getNotes());
		assertEquals(1, recipe.getDirectionSteps().size());
		assertEquals(1, recipe.getLabels().size());
		assertEquals(1, recipe.getIngredients().size());
	}
}