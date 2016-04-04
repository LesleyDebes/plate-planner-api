package com.debes.plateplanner.controller;

import com.debes.plateplanner.core.DishService;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishListModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lesley.debes
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public DishTypeListModel getDishTypes() {
        return dishService.getDishTypes();
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.POST)
    public DishModel addDish(DishModel dishModel) {
        return dishService.addDish(dishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}/remove", method = RequestMethod.DELETE)
    public BaseModel removeDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return dishService.removeDish(idMeal, idDish);
    }

    @RequestMapping(value = "/dish/update", method = RequestMethod.POST)
    public DishModel updateDish(DishModel dishModel) {
        return dishService.updateDish(dishModel);
    }

    @RequestMapping(value = "/{idMeal}/dish/{idDish}", method = RequestMethod.GET)
    public DishModel getDish(@PathVariable Integer idMeal, @PathVariable Integer idDish) {
        return dishService.getDish(idMeal, idDish);
    }

    @RequestMapping(value = "/{idMeal}/dish/list", method = RequestMethod.GET)
    public DishListModel getDishes(@PathVariable Integer idMeal) {
        return dishService.getDishList(idMeal);
    }
}
