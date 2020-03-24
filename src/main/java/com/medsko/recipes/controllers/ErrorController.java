package com.medsko.recipes.controllers;

import com.medsko.recipes.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception", exception);
		ModelAndView modelAndView = new ModelAndView("404error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("400error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}

}
