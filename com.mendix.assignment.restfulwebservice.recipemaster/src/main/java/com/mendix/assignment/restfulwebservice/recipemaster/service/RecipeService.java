package com.mendix.assignment.restfulwebservice.recipemaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.Recipe;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.RecipeRepository;

import jakarta.transaction.Transactional;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	

	public RecipeService(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	

	@Transactional
	public Recipe SavedRecipe(Recipe recipe) {


		recipe.getIngredientslist().forEach(d -> d.setRecipe(recipe));
		recipe.getRecipeDirection().forEach(d -> d.setRecipe(recipe));

		return recipeRepository.save(recipe);

	}

	public Page<Recipe> getRecipes(Pageable page) {
		// TODO Auto-generated method stub
		return recipeRepository.findAll(page);
	}

	public Optional<Recipe> retriveRecipeByTitle(String name) {

		return recipeRepository.findByTitle(name);
	}

	public Optional<List<Recipe>> retriveRecipeForCategory(String name) {
		return recipeRepository.findBycategoryName(name);
	}
}