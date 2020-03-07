package com.medsko.recipes.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@Lob
	private byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DirectionStep> directionSteps = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "label_id"))
	private List<Label> labels = new ArrayList<>();

	public void addDirectionStep(DirectionStep directionStep) {
		directionSteps.add(directionStep);
	}

	public void addLabel(Label label) {
		if (label == null) return;
		label.addRecipe(this);
		labels.add(label);
	}

	public void addIngredient(Ingredient ingredient) {
		if (ingredient == null) return;
		ingredient.setRecipe(this);
		ingredients.add(ingredient);
	}

}
