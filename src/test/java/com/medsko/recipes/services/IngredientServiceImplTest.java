package com.medsko.recipes.services;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.converters.IngredientCommandToIngredient;
import com.medsko.recipes.converters.IngredientToIngredientCommand;
import com.medsko.recipes.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.medsko.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.medsko.recipes.model.Ingredient;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;

	@Spy
	IngredientToIngredientCommand ingredientToIngredientCommand =
			new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());

	@Spy
	IngredientCommandToIngredient ingredientCommandToIngredient =
			new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());

	@InjectMocks
	IngredientServiceImpl ingredientService;

	@Test
	void findByRecipeIdAndId() {
		// Given...
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setRecipe(recipe);
		recipe.addIngredient(ingredient);

		when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
		// ...when calling findByRecipeIdAndId()...
		IngredientCommand command = ingredientService.findByRecipeIdAndId(1L, 3L);
		// ...then the correct ingredient is returned.
		assertEquals(3L, command.getId());
		assertEquals(1L, command.getRecipeId());
		verify(recipeRepository).findById(anyLong());
	}

	@Test
	void saveIngredientCommand() {
		final Long recipeId = 2L;
		final Long ingredientId = 1L;
		final Long newIngredientId = 2L;

		IngredientCommand newIngredientCommand = new IngredientCommand();
		newIngredientCommand.setRecipeId(recipeId);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setDescription("Teaspoon");
		newIngredientCommand.setUnitOfMeasure(uomCommand);

		final Recipe existingRecipe = new Recipe();
		Ingredient existingIngredient = new Ingredient();
		existingIngredient.setId(ingredientId);
		existingRecipe.addIngredient(existingIngredient);
		existingRecipe.setId(recipeId);

		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
		when(recipeRepository.save(any())).then(invocation -> {
			existingRecipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId() == null)
					.forEach(ingredient -> ingredient.setId(newIngredientId));
			return existingRecipe;
		});

		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(newIngredientCommand);

		assertEquals(2, existingRecipe.getIngredients().size());
		assertEquals(newIngredientId, savedCommand.getId());
		verify(recipeRepository).findById(2L);
		verify(recipeRepository).save(any(Recipe.class));
	}

	@Test
	void deleteById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setRecipe(recipe);
		recipe.addIngredient(ingredient);

		when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

		ingredientService.deleteById(1L, 3L);

		assertTrue(recipe.getIngredients().isEmpty());
		verify(recipeRepository).findById(1L);
		verify(recipeRepository).save(any(Recipe.class));
	}
}