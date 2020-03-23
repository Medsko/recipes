package com.medsko.recipes.services;

import com.medsko.recipes.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);
}
