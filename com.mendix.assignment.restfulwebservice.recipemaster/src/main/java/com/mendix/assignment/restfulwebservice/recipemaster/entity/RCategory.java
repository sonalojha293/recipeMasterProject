package com.mendix.assignment.restfulwebservice.recipemaster.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class RCategory {

	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;

	@NotNull(message = "Category name is required")
	private String name;

	@ManyToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Recipe> recipelist;

	protected RCategory() {

	}

	public RCategory(String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipelist() {
		return recipelist;
	}

	public void setRecipelist(List<Recipe> recipelist) {
		this.recipelist = recipelist;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
