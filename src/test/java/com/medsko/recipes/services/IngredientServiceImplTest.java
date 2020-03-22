package com.medsko.recipes.services;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.converters.IngredientToIngredientCommand;
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
}