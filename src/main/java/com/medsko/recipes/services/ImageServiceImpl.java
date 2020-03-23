package com.medsko.recipes.services;

import com.medsko.recipes.exceptions.NotFoundException;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {

		Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new NotFoundException("Recipe with id " + recipeId + " could not be found!"));

		try {
			recipe.setImage(file.getBytes());
		} catch (IOException e) {
			log.error("Error getting the file contents!", e);
		}

		recipeRepository.save(recipe);
	}
}
