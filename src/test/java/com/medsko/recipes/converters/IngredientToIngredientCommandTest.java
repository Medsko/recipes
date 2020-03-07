package com.medsko.recipes.converters;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.model.Ingredient;
import com.medsko.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredientToIngredientCommandTest {

	private IngredientToIngredientCommand ingredientToIngredientCommand;

	@BeforeEach
	void setUp() {
		ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	void testNull() {
		assertNull(ingredientToIngredientCommand.convert(null));
	}

	@Test
	void testEmpty() {
		assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
	}

	@Test
	void convert() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(TestValues.INGREDIENT_ID);
		ingredient.setAmount(TestValues.AMOUNT);
		ingredient.setDescription(TestValues.DESCRIPTION);
		ingredient.setUnitOfMeasure(new UnitOfMeasure());

		IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);

		assertNotNull(command);
		assertNotNull(command.getUnitOfMeasure());
		assertEquals(TestValues.INGREDIENT_ID, command.getId());
		assertEquals(TestValues.AMOUNT, command.getAmount());
		assertEquals(TestValues.DESCRIPTION, command.getDescription());
	}
}