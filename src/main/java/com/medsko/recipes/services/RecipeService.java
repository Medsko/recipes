package com.medsko.recipes.services;

import com.medsko.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {

	Recipe findById(Long id);

	List<Recipe> listRecipes();
}
