package com.medsko.recipes.services;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

	private RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, null, null);
	}

	@Test
	void getRecipeByIdTest() {
		Long recipeId = 1L;
		Recipe recipe = new Recipe();
		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

		Recipe foundRecipe = recipeService.findById(recipeId);

		assertNotNull(foundRecipe, "Recipe could not be retrieved!");
		verify(recipeRepository).findById(anyLong());
	}

	@Test
	void listRecipesTest() {

		Recipe recipe = new Recipe();
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipes);

		assertEquals(1, recipeService.listRecipes().size());
	}

	@Test
	void testDeleteById() {
		Long idToDelete = 2L;
		recipeService.deleteById(idToDelete);

		verify(recipeRepository).deleteById(anyLong());
	}
}