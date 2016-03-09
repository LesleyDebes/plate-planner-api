package com.debes.plateplanner.core;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.RecipeCategoryListModel;
import com.debes.plateplanner.models.recipe.RecipeListModel;
import com.debes.plateplanner.models.recipe.RecipeModel;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void test_getRecipeCategoryList() {
        RecipeCategoryListModel recipeCategoryList = recipeService.getRecipeCategoryList();
        assertNotNull(recipeCategoryList);
        assertEquals(ModelStatusEnum.SUCCESS, recipeCategoryList.getModelStatusEnum());
        assertEquals(4, CollectionUtils.size(recipeCategoryList.getRecipeCategoryList()));
    }

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
    public void test_upsertRecipe() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setRecipeName("Lasagna");
        recipeModel.setRecipeSource("Epicurious.com");
        recipeModel = recipeService.upsertRecipe(recipeModel);
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
        recipeModel = recipeService.upsertRecipe(recipeModel);
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
}
