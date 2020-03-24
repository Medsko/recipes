package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.model.Recipe;
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

import static org.mockito.ArgumentMatchers.anyLong;
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

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ErrorController())
				.build();
	}

	@Test
	void showById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.findById(1L)).thenReturn(recipe);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/showRecipe"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testNewRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeForm"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testGetUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeForm"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testDeleteAction() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

	@Test
	void handleNumberFormatException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/qwer/update"))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("400error"));
	}
}