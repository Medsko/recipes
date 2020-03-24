package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;

	public final static String RECIPE_FORM_URL = "recipe/recipeForm";

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/showRecipe";
	}

	@GetMapping("new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return RECIPE_FORM_URL;
	}

	@GetMapping("{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
		return RECIPE_FORM_URL;
	}

	@PostMapping
	public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(objectError -> log.debug("Binding failure: " + objectError.toString()));
			return RECIPE_FORM_URL;
		}

		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		log.debug("Deleting recipe with id: " + id);
		recipeService.deleteById(new Long(id));
		return "redirect:/";
	}

}
