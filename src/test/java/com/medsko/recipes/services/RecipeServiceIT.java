package com.medsko.recipes.services;

import com.medsko.recipes.TestValues;
import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.converters.RecipeToRecipeCommand;
import com.medsko.recipes.model.Recipe;
import com.medsko.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecipeServiceIT {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeToRecipeCommand recipeToRecipeCommand;

	@Transactional
	@Test
	void testSaveWithNewDescription() {
		Recipe recipe = recipeRepository.findAll().iterator().next();
		RecipeCommand command = recipeToRecipeCommand.convert(recipe);
		assert command != null;

		command.setDescription(TestValues.NEW_DESCRIPTION);
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		assertEquals(TestValues.NEW_DESCRIPTION, savedCommand.getDescription());
		assertEquals(recipe.getIngredients().size(), savedCommand.getIngredients().size());
		assertEquals(recipe.getLabels().size(), savedCommand.getLabels().size());
		assertEquals(recipe.getId(), savedCommand.getId());
	}

}
