package com.mendix.assignment.restfulwebservice.recipemaster.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {

	public RecipeNotFoundException(String message) {
		super(message);
	}

}
