package com.medsko.recipes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabelTest {

	private Label label;

	@BeforeEach
	void setUp() {
		label = new Label();
	}

	@Test
	void addRecipe() {
		Recipe recipe = new Recipe();
		recipe.setDescription("test");
		recipe.addLabel(label);
		assertTrue(label.getRecipes().contains(recipe));
		assertTrue(recipe.getLabels().contains(label));
	}

	@Test
	void getId() {
		label.setId(4L);
		assertEquals(4L, label.getId());
	}
}