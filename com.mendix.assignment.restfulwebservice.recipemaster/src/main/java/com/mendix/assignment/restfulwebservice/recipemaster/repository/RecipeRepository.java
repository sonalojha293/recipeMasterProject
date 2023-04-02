package com.mendix.assignment.restfulwebservice.recipemaster.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	Optional<List<Recipe>> findBycategoryName(String category);

	Optional<Recipe> findByTitle(String title);

}
