package com.medsko.recipes.commands;

import com.medsko.recipes.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 255)
	private String description;

	@Range(min = 1, max = 999)
	private Integer prepTime;

	@Max(999)
	private Integer cookTime;

	@Range(min = 1, max = 999)
	private Integer servings;
	private String source;

	@URL
	private String url;
	private Difficulty difficulty;
	private NotesCommand notes;
	private byte[] image;

	@Size(min = 1, max = 999)
	private List<DirectionStepCommand> directionSteps = new ArrayList<>();
	private List<IngredientCommand> ingredients = new ArrayList<>();
	private List<LabelCommand> labels = new ArrayList<>();
}
