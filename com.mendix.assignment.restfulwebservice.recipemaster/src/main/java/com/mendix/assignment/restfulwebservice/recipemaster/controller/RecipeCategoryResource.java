package com.mendix.assignment.restfulwebservice.recipemaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.RCategory;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.CategoryRepository;
import com.mendix.assignment.restfulwebservice.recipemaster.service.CategoryService;

@RestController
public class RecipeCategoryResource {

	/*
	 * This is the controller class to handle request related to recipe category
	 * 
	 * 
	 */


	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public List<RCategory> retriveAllCategory() {

		return categoryService.retriveAllCategory();

	}

	@GetMapping(path = "/categories/{name}")
	public List<RCategory> retriveAllCategoryByName(@PathVariable String name) {

		return categoryService.retriveAllCategoryByName(name);

	}

}
