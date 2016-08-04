package com.debes.plateplanner.controller;

import com.debes.plateplanner.core.DishService;
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

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MealModel createMeal(MealModel mealModel) {
        return mealService.addMeal(mealModel);
    }

    @RequestMapping(value = "/{idMeal}", method = RequestMethod.GET)
    public MealModel getMeal(@PathVariable Integer idMeal) {
        return mealService.getMeal(idMeal);
    }

    @RequestMapping(value = "/{idMeal}", method = RequestMethod.PUT)
    public MealModel updateMeal(@PathVariable Integer idMeal, MealModel updatedMealModel) {
        return mealService.updateMeal(idMeal, updatedMealModel);
    }

    @RequestMapping(value = "/{idMeal}", method = RequestMethod.DELETE)
    public BaseModel removeMeal(@PathVariable Integer idMeal) {
        return mealService.removeMeal(idMeal);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public MealTypeListModel getMealTypes() {
        return mealService.getMealTypes();
    }

    @RequestMapping(value = "/{idMeal}/dish", method = RequestMethod.POST)
    public DishModel createDish(DishModel dishModel) {
        return dishService.addDish(dishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}", method = RequestMethod.PUT)
    public DishModel updateDish(@PathVariable Integer idDish, DishModel updatedDishModel) {
        return dishService.updateDish(idDish, updatedDishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}", method = RequestMethod.GET)
    public DishModel getDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return dishService.getDish(idMeal, idDish);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}", method = RequestMethod.DELETE)
    public BaseModel removeDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return dishService.removeDish(idMeal, idDish);
    }

    @RequestMapping(value = "/{idMeal}/dish", method = RequestMethod.GET)
    public DishListModel getDishes(@PathVariable Integer idMeal) {
        return dishService.getDishList(idMeal);
    }

}
