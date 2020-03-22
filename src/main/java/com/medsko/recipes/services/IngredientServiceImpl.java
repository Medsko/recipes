package com.medsko.recipes.services;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.converters.IngredientToIngredientCommand;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId) {
		if (recipeId == null || ingredientId == null) {
			log.error("Both recipeId and ingredientId should be provided!");
			return new IngredientCommand();
		}

		Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
		if (!optionalRecipe.isPresent()) {
			log.error("Recipe with id " + recipeId + " could not be found!");
			return new IngredientCommand();
		}

		Recipe recipe = optionalRecipe.get();

		Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
				.filter(ingredient -> ingredientId.equals(ingredient.getId()))
				.map(ingredientToIngredientCommand::convert)
				.findFirst();

		if (!optionalIngredientCommand.isPresent()) {
			log.error("Ingredient with id " + ingredientId + " of recipe " + recipeId + " could not be found!");
			return new IngredientCommand();
		}

		return optionalIngredientCommand.get();
	}
}
