package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.dish.DishType;
import com.debes.plateplanner.dao.dish.repository.DishTypeRepository;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import com.debes.plateplanner.models.dish.DishTypeModel;
import com.debes.plateplanner.models.enums.DishTypeEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lesley.debes
 */
@Service
public class DishService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishTypeRepository dishTypeRepository;

    public DishTypeListModel getDishTypes() {
        DishTypeListModel dishTypeListModel = new DishTypeListModel();
        try {
            List<DishType> dishTypeList = dishTypeRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(dishTypeList)) {
                List<DishTypeModel> dishTypeModelList = new ArrayList<>();
                for (DishType dishType : dishTypeList) {
                    DishTypeModel dishTypeModel = new DishTypeModel();
                    dishTypeModel.setDishType(DishTypeEnum.get(dishType.getIdDishType()));
                    dishTypeModel.setDishTypeDescription(dishType.getDishType());
                    dishTypeModelList.add(dishTypeModel);
                }
                dishTypeListModel.setDishTypeList(dishTypeModelList);
                dishTypeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No dish types found.");
                dishTypeListModel.setDishTypeList(new ArrayList<>());
                dishTypeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                dishTypeListModel.setMessage("No dish types found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving dish types: ", e);
            dishTypeListModel.setDishTypeList(new ArrayList<>());
            dishTypeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishTypeListModel.setMessage("There was an error retrieving the list of dish types.");
        }
        return dishTypeListModel;
    }
}
