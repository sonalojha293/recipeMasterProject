package com.mendix.assignment.restfulwebservice.recipemaster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class RecipeDirection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition= "varchar(5000)")
	@NotNull(message="Cooking step is required")
	private String steps;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recipe_id",nullable=false)
	@JsonIgnore
	private Recipe recipe;
	
	protected RecipeDirection() {
		
	}
	
	
	

	public RecipeDirection(long id, String steps) {
		super();
		this.id = id;
		this.steps = steps;
		
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	

	public Recipe getRecipe() {
		return recipe;
	}


	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}




	@Override
	public String toString() {
		return "RecipeDirection [id=" + id + ", steps=" + steps + ", recipe=" + recipe + "]";
	}


	
	


}
