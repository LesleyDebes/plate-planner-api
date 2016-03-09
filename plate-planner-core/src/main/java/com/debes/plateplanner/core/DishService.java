package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.dish.DishType;
import com.debes.plateplanner.dao.dish.repository.DishTypeRepository;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import com.debes.plateplanner.models.dish.DishTypeModel;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lesley.debes
 */
@Service
public class DishService {
    //TODO:  ADD LOGGER
    @Autowired
    private DishTypeRepository dishTypeRepository;

    public DishTypeListModel getDishTypes() {
        DishTypeListModel dishTypeListModel = new DishTypeListModel();
        try {
            List<DishType> dishTypeList = dishTypeRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(dishTypeList)) {
                List<DishTypeModel> dishTypeModelList = new ArrayList<>();

                //TODO:  Use Lambdas?
                for (DishType dishType : dishTypeList) {
                    DishTypeModel dishTypeModel = new DishTypeModel();
                    dishTypeModel.setIdDishType(dishType.getIdDishType());
                    dishTypeModel.setDishType(dishType.getDishType());
                    dishTypeModelList.add(dishTypeModel);
                }
                dishTypeListModel.setDishTypeList(dishTypeModelList);
                dishTypeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                //TODO:  HOW TO THROW NO DATA FOUND EXCEPTION
                dishTypeListModel.setDishTypeList(new ArrayList<>());
                dishTypeListModel.setMessage("There was an error retrieving dish types");
            }
        } catch (Exception e) {
            dishTypeListModel.setDishTypeList(new ArrayList<>());
            dishTypeListModel.setMessage("There was an error retrieving dish types");
            //TODO:  LOGGER STATEMENT
        }

        return dishTypeListModel;
    }
}
