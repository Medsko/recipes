package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.exceptions.NotFoundException;
import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;

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
		return "recipe/recipeForm";
	}

	@GetMapping("{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
		return "recipe/recipeForm";
	}

	@PostMapping
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedCommand.getId();
	}

	@GetMapping("{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		log.debug("Deleting recipe with id: " + id);
		recipeService.deleteById(new Long(id));
		return "redirect:/";
	}

	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception", exception);
		ModelAndView modelAndView = new ModelAndView("404error.html");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}

}
