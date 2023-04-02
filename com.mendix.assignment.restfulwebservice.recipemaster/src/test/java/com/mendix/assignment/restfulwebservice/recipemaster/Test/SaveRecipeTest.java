package com.mendix.assignment.restfulwebservice.recipemaster.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mendix.assignment.restfulwebservice.recipemaster.entity.Ingredient;
import com.mendix.assignment.restfulwebservice.recipemaster.entity.Recipe;
import com.mendix.assignment.restfulwebservice.recipemaster.entity.RecipeDirection;
import com.mendix.assignment.restfulwebservice.recipemaster.repository.RecipeRepository;
import com.mendix.assignment.restfulwebservice.recipemaster.service.RecipeService;

@ExtendWith(MockitoExtension.class)
public class SaveRecipeTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void testSavedRecipe() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Test Recipe");
        recipe.setYeild(4);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Test Ingredient 1", 100, "gm"));
        ingredients.add(new Ingredient("Test Ingredient 2", 200,"gm"));
        recipe.setIngredientslist(ingredients);
        List<RecipeDirection> recipeDirections = new ArrayList<>();
        recipeDirections.add(new RecipeDirection(01111, "Test Step 1"));
        recipeDirections.add(new RecipeDirection(02222,"Test Step 2"));
        recipe.setRecipeDirection(recipeDirections);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1111111);
        savedRecipe.setTitle("Test Recipe");
        savedRecipe.setYeild(4);
        savedRecipe.setIngredientslist(ingredients);
        savedRecipe.setRecipeDirection(recipeDirections);

        when(recipeRepository.save(recipe)).thenReturn(savedRecipe);

        Recipe result = recipeService.SavedRecipe(recipe);

        assertEquals(1111111, result.getId());
        assertEquals("Test Recipe", result.getTitle());
        assertEquals(4, result.getYeild());
        assertEquals(ingredients, result.getIngredientslist());
        assertEquals(recipeDirections, result.getRecipeDirection());

        verify(recipeRepository, times(1)).save(recipe);
    }
}