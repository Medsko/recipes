package com.medsko.recipes.services;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.converters.RecipeCommandToRecipe;
import com.medsko.recipes.converters.RecipeToRecipeCommand;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Recipe findById(Long id) {
		return recipeRepository.findById(id).orElseThrow(()-> new RuntimeException("Recipe with id " + id + " not found!"));
	}

	@Override
	public List<Recipe> listRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {

		Recipe recipe = recipeCommandToRecipe.convert(command);
		Recipe savedRecipe = recipeRepository.save(recipe);
		log.debug("Saved recipe " + savedRecipe.getDescription() + " with id " + savedRecipe.getId());

		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	public RecipeCommand findCommandById(Long id) {
		return recipeToRecipeCommand.convert(findById(id));
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}

}
