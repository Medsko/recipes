package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.services.ImageService;
import com.medsko.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

	@Mock
	ImageService imageService;

	@Mock
	RecipeService recipeService;

	@InjectMocks
	ImageController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void getImageForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		when(recipeService.findCommandById(1L)).thenReturn(command);

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void newImage() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
				"file", "taco.png", "", "beautiful taco".getBytes());
		mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(file))
				.andExpect(status().is3xxRedirection());

		verify(imageService).saveImageFile(1L, file);
	}

	@Test
	void renderImageFromDB() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);
		command.setImage("fake image".getBytes());

		when(recipeService.findCommandById(1L)).thenReturn(command);

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeImage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		assertEquals(command.getImage().length, response.getContentAsByteArray().length);
	}
}