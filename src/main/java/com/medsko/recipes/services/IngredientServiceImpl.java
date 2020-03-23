package com.medsko.recipes.services;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.converters.IngredientCommandToIngredient;
import com.medsko.recipes.converters.IngredientToIngredientCommand;
import com.medsko.recipes.model.Ingredient;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeRepository recipeRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
	                             RecipeRepository recipeRepository,
	                             IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Recipe recipe = recipeRepository.findById(command.getRecipeId())
				.orElseThrow(()-> new IllegalStateException("Recipe " + command.getRecipeId() + " could not be found!"));

		if (command.getId() != null) {
			recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(command.getId()));
		}

		final Ingredient ingredientToSave = ingredientCommandToIngredient.convert(command);
		recipe.addIngredient(ingredientToSave);

		Recipe savedRecipe = recipeRepository.save(recipe);

		return savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientToSave.getId()))
				.map(ingredientToIngredientCommand::convert)
				.findFirst()
				.orElseThrow(()-> new RuntimeException("Failed to save the ingredient to the recipe!"));
	}
}
