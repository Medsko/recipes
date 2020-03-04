package com.medsko.recipes.services;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

	private RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void listRecipes() {

		Recipe recipe = new Recipe();
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipes);

		assertEquals(1, recipeService.listRecipes().size());
	}
}