package com.medsko.recipes.commands;

import com.medsko.recipes.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private Difficulty difficulty;
	private NotesCommand notes;

	private List<DirectionStepCommand> directionSteps = new ArrayList<>();
	private List<IngredientCommand> ingredients = new ArrayList<>();
	private List<LabelCommand> labels = new ArrayList<>();
}
