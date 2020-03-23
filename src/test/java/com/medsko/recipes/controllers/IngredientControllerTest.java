package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.IngredientCommand;
import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.commands.UnitOfMeasureCommand;
import com.medsko.recipes.services.IngredientService;
import com.medsko.recipes.services.RecipeService;
import com.medsko.recipes.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

	@Mock
	UnitOfMeasureService unitOfMeasureService;

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

	@Test
	void newIngredientForm() throws Exception {
		Long recipeId = 1L;
		RecipeCommand command = new RecipeCommand();
		command.setId(recipeId);

		when(recipeService.findCommandById(recipeId)).thenReturn(command);
		when(unitOfMeasureService.listUnitOfMeasureCommands()).thenReturn(new ArrayList<>());

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/ingredientForm"))
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(model().attributeExists("uomList"));
	}

	@Test
	void updateRecipeIngredient() throws Exception {
		IngredientCommand command = new IngredientCommand();
		when(ingredientService.findByRecipeIdAndId(1L, 2L)).thenReturn(command);

		List<UnitOfMeasureCommand> uoms = new ArrayList<>();
		uoms.add(new UnitOfMeasureCommand());
		uoms.add(new UnitOfMeasureCommand());
		when(unitOfMeasureService.listUnitOfMeasureCommands()).thenReturn(uoms);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/ingredientForm"))
				.andExpect(model().attributeExists("ingredient"));
	}

	@Test
	void saveOrUpdateIngredient() throws Exception {
		IngredientCommand command = new IngredientCommand();
		command.setRecipeId(1L);
		command.setId(2L);

		when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

		mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", "2")
					.param("description", "yummy stuff")
				)
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/1/ingredient/2/show"));
	}

	@Test
	void deleteIngredient() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/1/ingredients"));

		verify(ingredientService).deleteById(1L, 2L);
	}

}