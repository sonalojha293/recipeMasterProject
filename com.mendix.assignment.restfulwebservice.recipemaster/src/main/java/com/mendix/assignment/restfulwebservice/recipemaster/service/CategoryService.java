package com.mendix.assignment.restfulwebservice.recipemaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.RCategory;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryrepository;

	public CategoryService(CategoryRepository categoryrepository) {
		super();
		this.categoryrepository = categoryrepository;
	}

	public List<RCategory> retriveAllCategory() {
		// TODO Auto-generated method stub
		return categoryrepository.findAll();
	}

	public List<RCategory> retriveAllCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryrepository.findAllByName(name);
	}
	
	
	
	
	

}
