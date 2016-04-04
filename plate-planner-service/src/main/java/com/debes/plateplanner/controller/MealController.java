package com.debes.plateplanner.controller;

import com.debes.plateplanner.core.MealService;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishListModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.meal.MealModel;
import com.debes.plateplanner.models.meal.MealTypeListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lesley.debes
 */
@RestController
@RequestMapping("/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public MealTypeListModel getMealTypes() {
        return mealService.getMealTypes();
    }

    @RequestMapping(value = "/{idMeal}", method = RequestMethod.GET)
    public MealModel getMeal(@PathVariable Integer idMeal) {
        return mealService.getMeal(idMeal);
    }

    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    public MealModel upsertMeal(MealModel mealModel) {
        return mealService.upsertMeal(mealModel);
    }

    @RequestMapping(value = "/{idMeal}/remove", method = RequestMethod.DELETE)
    public BaseModel removeMeal(@PathVariable Integer idMeal) {
        return mealService.removeMeal(idMeal);
    }

    @RequestMapping(value = "/{idMeal}/dish/add", method = RequestMethod.POST)
    public DishModel addDish(@PathVariable Integer idMeal, DishModel dishModel) {
        return mealService.addDish(idMeal, dishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}/remove", method = RequestMethod.DELETE)
    public BaseModel removeDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return mealService.removeDish(idMeal, idDish);
    }

    @RequestMapping(value = "/{idMeal}/dish/update", method = RequestMethod.POST)
    public DishModel updateDish(@PathVariable Integer idMeal, DishModel dishModel) {
        return mealService.updateDish(idMeal, dishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}", method = RequestMethod.GET)
    public DishModel getDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return mealService.getDish(idMeal, idDish);
    }

    @RequestMapping(value = "/{idMeal}/dish/list", method = RequestMethod.GET)
    public DishListModel getDishes(@PathVariable Integer idMeal) {
        return mealService.getDishList(idMeal);
    }


}