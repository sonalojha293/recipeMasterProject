package com.mendix.assignment.restfulwebservice.recipemaster.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Recipe {

	@Id
	@GeneratedValue
	private long id;

	@NotBlank(message = "Unique Recipe Title is required")
	@Column(unique = true)
	private String title;

	@NotNull(message = "Yeild is required")
	private long yeild;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<RCategory> category;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
	private List<Ingredient> ingredientslist;
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
	private List<RecipeDirection> recipeDirection;

	public Recipe() {

	}

	public Recipe(String title, long yeild) {
		super();
		this.id = id;
		this.title = title;
		this.yeild = yeild;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getYeild() {
		return yeild;
	}

	public void setYeild(long yeild) {
		this.yeild = yeild;
	}

	public List<RCategory> getCategory() {
		return category;
	}

	public void setCategory(List<RCategory> category) {
		this.category = category;
	}

	public List<Ingredient> getIngredientslist() {
		return ingredientslist;
	}

	public void setIngredientslist(List<Ingredient> ingredientslist) {
		this.ingredientslist = ingredientslist;
	}

	public List<RecipeDirection> getRecipeDirection() {
		return recipeDirection;
	}

	public void setRecipeDirection(List<RecipeDirection> recipeDirection) {
		this.recipeDirection = recipeDirection;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", yeild=" + yeild + ", category=" + category
				+ ", ingredientslist=" + ingredientslist + ", recipeDirection=" + recipeDirection + "]";
	}

}
