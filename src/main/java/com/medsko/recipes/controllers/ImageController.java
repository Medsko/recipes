package com.medsko.recipes.controllers;

import com.medsko.recipes.commands.RecipeCommand;
import com.medsko.recipes.services.ImageService;
import com.medsko.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}")
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;

	public ImageController(ImageService imageService, RecipeService recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@GetMapping("/image")
	public String showImageForm(@PathVariable String recipeId, Model model) {

		model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));
		return "recipe/imageUploadForm";
	}

	@GetMapping("/recipeImage")
	public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
		RecipeCommand command = recipeService.findCommandById(new Long(recipeId));

		response.setContentType("image/jpeg");
		response.getOutputStream().write(command.getImage());
	}

	@PostMapping("/image")
	public String newImage(@PathVariable String recipeId, @RequestParam("file") MultipartFile file) {

		imageService.saveImageFile(new Long(recipeId), file);
		return "redirect:/recipe/" + recipeId + "/show";
	}
}
