package com.mendix.assignment.restfulwebservice.recipemaster.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.Recipe;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.RecipeRepository;
import com.mendix.assignment.restfulwebservice.recipemaster.service.RecipeService;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void testGetRecipes() {
        List<Recipe> recipes = Collections.singletonList(new Recipe());
        Page<Recipe> page = new PageImpl<>(recipes);
        when(recipeRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Recipe> result = recipeService.getRecipes(Pageable.unpaged());

        Assertions.assertEquals(recipes, result.getContent());
        Assertions.assertEquals(page.getTotalElements(), result.getTotalElements());
    }
    
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void testRetrieveRecipeByTitleWithValidName() {
        Recipe recipe = new Recipe();
        recipe.setTitle("5 min Sandwich");
        when(recipeRepository.findByTitle("5 min Sandwich")).thenReturn(Optional.of(recipe));

        Optional<Recipe> result = recipeService.retriveRecipeByTitle("5 min Sandwich");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("5 min Sandwich", result.get().getTitle());
    }

    @Test
    public void testRetrieveRecipeByTitleWithInvalidName() {
        when(recipeRepository.findByTitle("Quick Bite")).thenReturn(Optional.empty());

        Optional<Recipe> result = recipeService.retriveRecipeByTitle("Quick Bite");

        Assertions.assertFalse(result.isPresent());
    }
    
   

    @Test
    public void testRetrieveRecipeForCategoryWithValidName() {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("5 min Sandwich");
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Quick Bite");
        recipes.add(recipe2);
        when(recipeRepository.findBycategoryName("Sandwich")).thenReturn(Optional.of(recipes));

        Optional<List<Recipe>> result = recipeService.retriveRecipeForCategory("Sandwich");

        Assertions.assertTrue(result.isPresent());
        List<Recipe> resultList = result.get();
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals("5 min Sandwich", resultList.get(0).getTitle());
        Assertions.assertEquals("Quick Bite", resultList.get(1).getTitle());
    }

    @Test
    public void testRetrieveRecipeForCategoryWithInvalidName() {
        when(recipeRepository.findBycategoryName("Small Bite")).thenReturn(Optional.empty());

        Optional<List<Recipe>> result = recipeService.retriveRecipeForCategory("Small Bite");

        Assertions.assertFalse(result.isPresent());
    }
}
