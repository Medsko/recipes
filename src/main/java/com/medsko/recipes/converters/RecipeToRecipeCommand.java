package com.medsko.recipes.converters;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final LabelToLabelCommand labelToLabelCommand;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	private final DirectionStepToDirectionStepCommand directionStepToDirectionStepCommand;

	public RecipeToRecipeCommand(LabelToLabelCommand labelToLabelCommand,
	                             IngredientToIngredientCommand ingredientToIngredientCommand,
	                             NotesToNotesCommand notesToNotesCommand,
	                             DirectionStepToDirectionStepCommand directionStepToDirectionStepCommand) {
		this.labelToLabelCommand = labelToLabelCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.notesToNotesCommand = notesToNotesCommand;
		this.directionStepToDirectionStepCommand = directionStepToDirectionStepCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}

		final RecipeCommand command = new RecipeCommand();
		command.setId(source.getId());
		command.setDescription(source.getDescription());
		command.setPrepTime(source.getPrepTime());
		command.setCookTime(source.getCookTime());
		command.setServings(source.getServings());
		command.setSource(source.getSource());
		command.setUrl(source.getUrl());
		command.setDifficulty(source.getDifficulty());
		command.setImage(source.getImage());

		command.setNotes(notesToNotesCommand.convert(source.getNotes()));
		source.getDirectionSteps().forEach(directionStep ->
				command.getDirectionSteps().add(directionStepToDirectionStepCommand.convert(directionStep)));
		source.getLabels().forEach(label -> command.getLabels().add(labelToLabelCommand.convert(label)));
		source.getIngredients().forEach(ingredient ->
				command.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));

		return command;
	}
}
