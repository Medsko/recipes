package com.medsko.recipes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "labels")
	private List<Recipe> recipes = new ArrayList<>();

	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}

}
