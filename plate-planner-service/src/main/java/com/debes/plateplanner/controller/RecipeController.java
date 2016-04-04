package com.debes.plateplanner.controller;

import com.debes.plateplanner.core.RecipeService;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.recipe.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lesley.debes
 */
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    public RecipeModel upsertRecipe(RecipeModel recipeModel) {
        return recipeService.upsertRecipe(recipeModel);
    }

    @RequestMapping(value = "/{idRecipe}", method = RequestMethod.POST)
    public RecipeModel getRecipe(@PathVariable Integer idRecipe) {
        return recipeService.getRecipe(idRecipe);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RecipeListModel getRecipeList() {
        return recipeService.getRecipeList();
    }

    @RequestMapping(value = "/{idRecipe}/remove", method = RequestMethod.DELETE)
    public BaseModel removeRecipe(@PathVariable Integer idRecipe) {
        return recipeService.removeRecipe(idRecipe);
    }

    @RequestMapping(value = "/ingredient/add", method = RequestMethod.POST)
    public RecipeIngredientModel addRecipeIngredient(RecipeIngredientModel recipeIngredientModel) {
        return recipeService.addIngredient(recipeIngredientModel);
    }

    @RequestMapping(value = "/ingredient/update", method = RequestMethod.POST)
    public RecipeIngredientModel updateRecipeIngredient(RecipeIngredientModel recipeIngredientModel) {
        return recipeService.updateIngredient(recipeIngredientModel);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient/{idIngredient}/remove", method = RequestMethod.DELETE)
    public BaseModel removeRecipeIngredient(@PathVariable Integer idRecipe, @PathVariable Integer idIngredient) {
        return recipeService.removeIngredient(idRecipe, idIngredient);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient/all/remove", method = RequestMethod.DELETE)
    public BaseModel removeAllRecipeIngredients(@PathVariable Integer idRecipe) {
        return recipeService.removeAllIngredients(idRecipe);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient/list", method = RequestMethod.GET)
    public RecipeIngredientListModel getRecipeIngredients(@PathVariable Integer idRecipe) {
        return recipeService.getRecipeIngredientList(idRecipe);
    }

    /*@RequestMapping(value = "/{idRecipe}/category/{idCategory}/add", method = RequestMethod.POST)
    public BaseModel addRecipeCategory(@PathVariable Integer idRecipe, @PathVariable Integer idCategory) {
        return recipeService.addRecipeCategory(idRecipe, idCategory);
    }

    @RequestMapping(value = "/{idRecipe}/category/{idCategory}/remove", method = RequestMethod.POST)
    public BaseModel removeRecipeCategory(@PathVariable Integer idRecipe, @PathVariable Integer idCategory) {
        return recipeService.removeRecipeCategory(idRecipe, idCategory);
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public RecipeCategoryListModel getRecipeCategories() {
        return recipeService.getRecipeCategoryList();
    }
*/
}
