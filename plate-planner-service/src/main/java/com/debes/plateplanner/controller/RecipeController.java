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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RecipeModel addRecipe(RecipeModel recipeModel) {
        return recipeService.createRecipe(recipeModel);
    }

    @RequestMapping(value = "/{idRecipe}", method = RequestMethod.PUT)
    public RecipeModel updateRecipe(@PathVariable Integer idRecipe, RecipeModel recipeModel) {
        return recipeService.updateRecipe(idRecipe, recipeModel);
    }

    @RequestMapping(value = "/{idRecipe}", method = RequestMethod.GET)
    public RecipeModel getRecipe(@PathVariable Integer idRecipe) {
        return recipeService.getRecipe(idRecipe);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public RecipeListModel getRecipeList() {
        return recipeService.getRecipeList();
    }

    @RequestMapping(value = "/{idRecipe}", method = RequestMethod.DELETE)
    public BaseModel removeRecipe(@PathVariable Integer idRecipe) {
        return recipeService.removeRecipe(idRecipe);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient", method = RequestMethod.POST)
    public IngredientModel addRecipeIngredient(@PathVariable Integer idRecipe, IngredientModel ingredientModel) {
        return recipeService.addIngredient(idRecipe, ingredientModel);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient/{idIngredient}", method = RequestMethod.PUT)
    public IngredientModel updateRecipeIngredient(@PathVariable Integer idRecipe,
                                                  @PathVariable Integer idIngredient,
                                                  IngredientModel ingredientModel) {
        return recipeService.updateIngredient(idRecipe, idIngredient, ingredientModel);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient/{idIngredient}", method = RequestMethod.DELETE)
    public BaseModel removeRecipeIngredient(@PathVariable Integer idRecipe, @PathVariable Integer idIngredient) {
        return recipeService.removeIngredient(idRecipe, idIngredient);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient", method = RequestMethod.DELETE)
    public BaseModel removeAllRecipeIngredients(@PathVariable Integer idRecipe) {
        return recipeService.removeAllIngredients(idRecipe);
    }

    @RequestMapping(value = "/{idRecipe}/ingredient", method = RequestMethod.GET)
    public IngredientListModel getRecipeIngredients(@PathVariable Integer idRecipe) {
        return recipeService.getRecipeIngredientList(idRecipe);
    }

}
