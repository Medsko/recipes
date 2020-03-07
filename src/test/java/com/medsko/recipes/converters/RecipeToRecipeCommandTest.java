package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.model.DirectionStep;
import com.medsko.recipes.model.Ingredient;
import com.medsko.recipes.model.Label;
import com.medsko.recipes.model.Notes;
import com.medsko.recipes.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeToRecipeCommandTest {

	private RecipeToRecipeCommand recipeToRecipeCommand;

	@BeforeEach
	void setUp() {
		recipeToRecipeCommand = new RecipeToRecipeCommand(
				new LabelToLabelCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand(),
				new DirectionStepToDirectionStepCommand()
		);
	}

	@Test
	void testNull() {
		assertNull(recipeToRecipeCommand.convert(null));
	}

	@Test
	void testEmpty() {
		assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
	}

	@Test
	void convert() {
		Recipe recipe = new Recipe();
		recipe.setId(TestValues.RECIPE_ID);
		recipe.setCookTime(TestValues.COOK_TIME);
		recipe.setDescription(TestValues.DESCRIPTION);
		recipe.setPrepTime(TestValues.PREP_TIME);
		recipe.setServings(TestValues.SERVINGS);
		recipe.setSource(TestValues.SOURCE);
		recipe.setUrl(TestValues.URL);
		recipe.setDifficulty(TestValues.DIFFICULTY);

		recipe.setNotes(new Notes());
		recipe.getLabels().add(new Label());
		recipe.getIngredients().add(new Ingredient());
		recipe.getDirectionSteps().add(new DirectionStep());

		RecipeCommand command = recipeToRecipeCommand.convert(recipe);

		assertNotNull(command);
		assertEquals(TestValues.RECIPE_ID, command.getId());
		assertEquals(TestValues.COOK_TIME, command.getCookTime());
		assertEquals(TestValues.DESCRIPTION, command.getDescription());
		assertEquals(TestValues.PREP_TIME, command.getPrepTime());
		assertEquals(TestValues.SERVINGS, command.getServings());
		assertEquals(TestValues.SOURCE, command.getSource());
		assertEquals(TestValues.URL, command.getUrl());
		assertEquals(TestValues.DIFFICULTY, command.getDifficulty());

		assertNotNull(command.getNotes());
		assertEquals(1, command.getDirectionSteps().size());
		assertEquals(1, command.getLabels().size());
		assertEquals(1, command.getIngredients().size());
	}
}