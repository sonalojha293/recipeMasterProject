package com.mendix.assignment.restfulwebservice.recipemaster.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.Recipe;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.CategoryRepository;
import com.mendix.assignment.restfulwebservice.recipemaster.service.RecipeService;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
public class RecipeResource {

	/*
	 * This is the controller class to handle request related to recipe
	 * 
	 * 
	 */

	@Autowired
	private CategoryRepository categoryrepository;

	@Autowired
	private RecipeService recipeService;

	

	public RecipeResource(RecipeService recipeService2) {
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * Retrieves all the recipe stored in database
	 * 
	 * @return Response Entity containing all the recipe details available; also
	 * supports paginated response
	 * 
	 */

	@GetMapping("/recipes")
	public ResponseEntity<Page<Recipe>> retriveAllRecipes(@Parameter(hidden = true)Pageable page) {
		return ResponseEntity.ok(recipeService.getRecipes(page));
	}
	
	/*
	 * Retrieves all the recipe stored in database based on title 
	 * 
	 * @return Response Entity containing  the recipe details available;
	 * 
	 */

	@GetMapping(path = "/recipes/{title}")
	public ResponseEntity<Object> retriveRecipeByTitle(@PathVariable String title) {

		Optional<Recipe> recipe = recipeService.retriveRecipeByTitle(title);
		if (recipe.isPresent())
			return ResponseEntity.ok().body(recipe.get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe Not Found");

	}
	
	/*
	 * Save a recipe  in database 
	 * 
	 * @return Response Entity containing  URI with new recipe id and recipe body is returned ;
	 * 
	 */

	
	  @PostMapping("/recipes") public ResponseEntity<Object>
	  createRecipe(@RequestBody @Valid Recipe recipe) { Recipe savedRecipe = null;
	  
	  try { savedRecipe = recipeService.SavedRecipe(recipe); } catch (Exception e)
	  { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	  body("Error saving recipe");
	  
	  } if (savedRecipe == null) { return
	  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	  body("Error saving recipe"); }
	  
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	  .buildAndExpand(savedRecipe.getId()).toUri(); return
	  ResponseEntity.created(location).body(savedRecipe);
	  
	  }
	 
	
	  /*
		 * Retrieves all the recipe stored in database based pertaining to given category
		 * 
		 * @return Response Entity containing  the recipe details available;
		 * 
		 */
	


	@GetMapping(path = "/categories/{name}/recipes")

	public ResponseEntity<Object> retriveRecipeForCategory(@PathVariable String name) {

		Optional<List<Recipe>> optionalRecipe = recipeService.retriveRecipeForCategory(name);

		if (optionalRecipe.isPresent())

			return ResponseEntity.ok().body(optionalRecipe.get());

		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe Not Found");

	}

}
