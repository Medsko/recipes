package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.services.IngredientService;
import com.medsko.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	IngredientController controller;

	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void listIngredients() throws Exception {
		RecipeCommand command = new RecipeCommand();
		when(recipeService.findCommandById(1L)).thenReturn(command);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(model().attributeExists("recipe"));
		verify(recipeService).findCommandById(1L);
	}

	@Test
	void showIngredient() throws Exception {
		IngredientCommand command = new IngredientCommand();
		when(ingredientService.findByRecipeIdAndId(1L, 2L)).thenReturn(command);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"));
	}
}