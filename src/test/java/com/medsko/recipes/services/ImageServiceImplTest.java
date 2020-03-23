package com.medsko.recipes.services;

import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	ImageServiceImpl imageService;

	@Test
	void saveImageFile() throws IOException {
		Long recipeId = 1L;
		Recipe recipe = new Recipe();
		recipe.setId(recipeId);

		MultipartFile file = new MockMultipartFile(
				"image", "something.png", "text/plain", "asdfasdfasdf".getBytes());

		when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

		// when
		imageService.saveImageFile(recipeId, file);

		verify(recipeRepository).save(argumentCaptor.capture());
		Recipe savedRecipe = argumentCaptor.getValue();
		assertEquals(file.getBytes().length, savedRecipe.getImage().length);
	}

}