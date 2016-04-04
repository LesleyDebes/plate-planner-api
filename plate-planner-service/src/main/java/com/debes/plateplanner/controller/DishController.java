package com.debes.plateplanner.controller;

import com.debes.plateplanner.core.DishService;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import org.springframework.beans.factory.annotation.Autowired;
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
}
