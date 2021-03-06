package com.debes.plateplanner.core;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MeasurementEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author lesley.debes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ContextConfiguration("classpath:plate-planner-test-core.xml")
public class RecipeServiceTest {
    @Autowired
    private RecipeService recipeService;

    @Test
    public void test_getRecipe() {
        RecipeModel recipeModel = recipeService.getRecipe(3);
        assertNotNull(recipeModel);
        assertEquals(ModelStatusEnum.SUCCESS, recipeModel.getModelStatusEnum());
        assertEquals("Vanilla Caramels", recipeModel.getRecipeName());
        assertEquals("Grandma Marjorie", recipeModel.getRecipeSource());
        assertNotNull(recipeModel.getCreateTimestamp());
        assertNotNull(recipeModel.getUpdateTimestamp());
    }

    @Test
    @Transactional
    public void test_createRecipe_updateRecipe() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setRecipeName("Lasagna");
        recipeModel.setRecipeSource("Epicurious.com");
        recipeModel = recipeService.createRecipe(recipeModel);
        assertNotNull(recipeModel);
        assertNotNull(recipeModel.getIdRecipe());
        assertEquals(ModelStatusEnum.SUCCESS, recipeModel.getModelStatusEnum());

        recipeModel = recipeService.getRecipe(recipeModel.getIdRecipe());
        assertNotNull(recipeModel);
        assertEquals(ModelStatusEnum.SUCCESS, recipeModel.getModelStatusEnum());
        assertEquals("Lasagna", recipeModel.getRecipeName());
        assertEquals("Epicurious.com", recipeModel.getRecipeSource());

        Integer savedRecipeID = recipeModel.getIdRecipe();
        recipeModel = new RecipeModel();
        recipeModel.setIdRecipe(savedRecipeID);
        recipeModel.setRecipeName("Best Lasagna");
        recipeModel.setRecipeSource("Epicurious");
        recipeModel = recipeService.updateRecipe(savedRecipeID, recipeModel);
        assertNotNull(recipeModel);
        assertNotNull(recipeModel.getIdRecipe());
        assertEquals(ModelStatusEnum.SUCCESS, recipeModel.getModelStatusEnum());
        assertEquals("Best Lasagna", recipeModel.getRecipeName());
        assertEquals("Epicurious", recipeModel.getRecipeSource());
    }

    @Test
    @Transactional
    public void test_removeRecipe() {
        BaseModel baseModel = recipeService.removeRecipe(3);
        assertNotNull(baseModel);
        assertEquals(ModelStatusEnum.SUCCESS, baseModel.getModelStatusEnum());
    }

    @Test
    public void test_getRecipeList() {
        RecipeListModel recipeListModel = recipeService.getRecipeList();
        assertNotNull(recipeListModel);
        assertTrue(CollectionUtils.isNotEmpty(recipeListModel.getRecipeModelList()));
        assertEquals(ModelStatusEnum.SUCCESS, recipeListModel.getModelStatusEnum());
        assertTrue(recipeListModel.getRecipeModelList().stream().allMatch(
                recipe -> recipe.getModelStatusEnum().isSuccessful()));
    }

    @Test
    @Transactional
    public void test_addIngredient_and_updateIngredient_and_removeIngredient() {
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setOrderSequence((short)2);
        ingredientModel.setIngredientMeasurementAmount("1");
        ingredientModel.setMeasurement(MeasurementEnum.BOX);
        ingredientModel.setIngredientName("lasagna noodles");
        ingredientModel = recipeService.addIngredient(3, ingredientModel);
        assertNotNull(ingredientModel);
        assertTrue(ingredientModel.getModelStatusEnum().isSuccessful());
        assertNotNull(ingredientModel.getIdIngredient());

        ingredientModel.setIngredientMeasurementAmount(".25");
        IngredientModel updatedIngredientModel = recipeService.updateIngredient(3, ingredientModel.getIdIngredient(),
                                                                                ingredientModel);
        assertNotNull(updatedIngredientModel);
        assertTrue(updatedIngredientModel.getModelStatusEnum().isSuccessful());

        BaseModel baseModel = recipeService.removeIngredient(3, updatedIngredientModel.getIdIngredient());
        assertNotNull(baseModel);
        assertTrue(baseModel.getModelStatusEnum().isSuccessful());
    }

    @Test
    @Transactional
    public void test_removeAllIngredients() {
        BaseModel baseModel = recipeService.removeAllIngredients(3);
        assertNotNull(baseModel);
        assertTrue(baseModel.getModelStatusEnum().isSuccessful());
    }

    @Test
    public void test_getRecipeIngredientList() {
        IngredientListModel recipeIngredientListModel = recipeService.getRecipeIngredientList(3);
        assertNotNull(recipeIngredientListModel);
        assertTrue(CollectionUtils.isNotEmpty(recipeIngredientListModel.getIngredientModelList()));
        assertTrue(recipeIngredientListModel.getModelStatusEnum().isSuccessful());
        assertTrue(recipeIngredientListModel.getIngredientModelList().stream().allMatch(i -> i.getModelStatusEnum().isSuccessful()));
    }

}
