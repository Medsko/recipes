package com.medsko.recipes;

import com.medsko.recipes.model.Difficulty;

import java.math.BigDecimal;

public interface TestValues {

	Long RECIPE_ID = 1L;
	Long NOTES_ID = 2L;
	Long LABEL_ID = 3L;
	Long UNIT_OF_MEASURE_ID = 4L;
	Long DIRECTION_STEP_ID = 5L;
	Long INGREDIENT_ID = 6L;
	Integer COOK_TIME = 5;
	Integer PREP_TIME = 7;
	String DESCRIPTION = "Description";
	String RECIPE_NOTES = "Useful notes";
	String NAME = "Henk";
	String SOURCE = "Source";
	String URL = "Some URL";
	Difficulty DIFFICULTY = Difficulty.EASY;
	Integer SERVINGS = 3;
	Integer STEP_NUMBER = 1;
	BigDecimal AMOUNT = new BigDecimal(1);

}
