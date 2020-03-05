package com.medsko.recipes.controllers;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

	@Mock
	RecipeService mockService;

	@Mock
	Model model;

	private IndexController indexController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(mockService);
	}

	@Test
	void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	void getIndexPage() {

		// Given two recipes present in the repository...
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		when(mockService.listRecipes()).thenReturn(recipes);

		ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

		// ...when getIndexPage() is called...
		String viewName = indexController.getIndexPage(model);

		// ...then the correct template name is returned...
		assertEquals("index", viewName);
		// ...and the methods of the mocked objects have both been called once...
		verify(mockService, times(1)).listRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		// ...and the number of recipes matches the number of recipes in the mock list.
		assertEquals(recipes.size(), argumentCaptor.getValue().size());
	}
}