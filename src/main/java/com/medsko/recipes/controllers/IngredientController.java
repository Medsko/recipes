package com.medsko.recipes.controllers;

import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		log.debug("Getting list of ingredients for recipe " + recipeId);
		model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));
		return "recipe/ingredient/list";
	}

}
