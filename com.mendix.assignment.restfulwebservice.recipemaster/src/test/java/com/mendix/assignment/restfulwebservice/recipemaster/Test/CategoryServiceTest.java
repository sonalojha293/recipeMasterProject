package com.mendix.assignment.restfulwebservice.recipemaster.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.RCategory;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.CategoryRepository;
import com.mendix.assignment.restfulwebservice.recipemaster.service.CategoryService;

class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void testRetrieveAllCategory() {
        List<RCategory> categories = new ArrayList<>();
		
		  categories.add(new RCategory("Desserts")); categories.add(new
		  RCategory("Appetizers"));
		 

        when(categoryRepository.findAll()).thenReturn(categories);

        List<RCategory> result = categoryService.retriveAllCategory();

        Assertions.assertEquals(2, result.size());
    }
    
    @Test
    public void testRetrieveAllCategoryByName() {
		// mock the category repository
		CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
		
		// create some sample categories
		RCategory category1 = new RCategory("Test Category");
	
	
		
		RCategory category2 = new RCategory("Test Category2");
		
		
		// mock findAllByName method to return the sample categories
		Mockito.when(categoryRepositoryMock.findAllByName("Category")).thenReturn(Arrays.asList(category1, category2));
		
		// create a new instance of CategoryService with the mocked repository
		CategoryService categoryService = new CategoryService(categoryRepositoryMock);
		
		// call the retrieveAllCategoryByName method
		List<RCategory> categories = categoryService.retriveAllCategoryByName("Category");
		
		// assert that the correct categories were returned
		assertEquals(2, categories.size());
		assertEquals("Test Category", categories.get(0).getName());
		assertEquals("Test Category2", categories.get(1).getName());
	}

  
}