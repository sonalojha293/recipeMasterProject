package com.mendix.assignment.restfulwebservice.recipemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.RCategory;

public interface CategoryRepository extends JpaRepository<RCategory, Long> {

	List<RCategory> findAllByName(String name);

}
