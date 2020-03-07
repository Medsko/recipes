package com.medsko.recipes.converters;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final LabelCommandToLabel labelCommandToLabel;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final NotesCommandToNotes notesCommandToNotes;
	private final DirectionStepCommandToDirectionStep directionStepCommandToDirectionStep;

	public RecipeCommandToRecipe(LabelCommandToLabel labelCommandToLabel, IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes, DirectionStepCommandToDirectionStep directionStepCommandToDirectionStep) {
		this.labelCommandToLabel = labelCommandToLabel;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.notesCommandToNotes = notesCommandToNotes;
		this.directionStepCommandToDirectionStep = directionStepCommandToDirectionStep;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}

		Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setUrl(source.getUrl());
		recipe.setSource(source.getSource());
		recipe.setServings(source.getServings());
		recipe.setDifficulty(source.getDifficulty());

		recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
		source.getDirectionSteps().forEach(directionStepCommand ->
				recipe.addDirectionStep(directionStepCommandToDirectionStep.convert(directionStepCommand)));
		source.getIngredients().forEach(ingredientCommand ->
				recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand)));
		source.getLabels().forEach(labelCommand ->
				recipe.addLabel(labelCommandToLabel.convert(labelCommand)));

		return recipe;
	}
}
