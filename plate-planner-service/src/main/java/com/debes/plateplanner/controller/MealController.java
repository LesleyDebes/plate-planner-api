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



}
