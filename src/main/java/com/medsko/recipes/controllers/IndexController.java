package com.medsko.recipes.controllers;

import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.listRecipes());
		log.debug("Recipes added to model.");
		return "index";
	}
}
