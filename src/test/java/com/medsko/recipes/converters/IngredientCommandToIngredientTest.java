package com.medsko.recipes.converters;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredientCommandToIngredientTest {

	private final static Long ID = 1L;
	private final static BigDecimal AMOUNT = new BigDecimal(1);
	private final static String DESCRIPTION = "Carrot/stick";

	private IngredientCommandToIngredient ingredientCommandToIngredient;

	@BeforeEach
	void setUp() {
		ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	void testNull() {
		assertNull(ingredientCommandToIngredient.convert(null));
	}

	@Test
	void testEmpty() {
		assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
	}

	@Test
	void convert() {

		IngredientCommand command = new IngredientCommand();
		command.setId(ID);
		command.setAmount(AMOUNT);
		command.setDescription(DESCRIPTION);
		command.setUnitOfMeasure(new UnitOfMeasureCommand());

		Ingredient ingredient = ingredientCommandToIngredient.convert(command);

		assertNotNull(ingredient);
		assertNotNull(ingredient.getUnitOfMeasure());
		assertEquals(ID, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
	}
}