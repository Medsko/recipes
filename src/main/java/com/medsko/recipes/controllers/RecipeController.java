package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/showRecipe";
	}

	@RequestMapping("new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeForm";
	}

	@RequestMapping("{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
		return "recipe/recipeForm";
	}

	@PostMapping
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedCommand.getId();
	}

	@RequestMapping("{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		log.debug("Deleting recipe with id: " + id);
		recipeService.deleteById(new Long(id));
		return "redirect:/";
	}

}
