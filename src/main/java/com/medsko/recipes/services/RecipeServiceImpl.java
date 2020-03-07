package com.medsko.recipes.services;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
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

//	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
//		return
//	}
}
