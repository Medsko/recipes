package com.medsko.recipes.controllers;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

	@Mock
	private RecipeService recipeService;

	@InjectMocks
	private RecipeController controller;

	@Test
	void showById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		when(recipeService.findById(1L)).thenReturn(recipe);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipes/showRecipe"))
				.andExpect(model().attributeExists("recipe"));
	}
}