package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.dish.Dish;
import com.debes.plateplanner.dao.dish.DishType;
import com.debes.plateplanner.dao.dish.repository.DishRepository;
import com.debes.plateplanner.dao.dish.repository.DishTypeRepository;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishListModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import com.debes.plateplanner.models.dish.DishTypeModel;
import com.debes.plateplanner.models.enums.DishTypeEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Autowired
    private DishRepository dishRepository;

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

    @Transactional
    public DishModel addDish(DishModel dishModel) {
        try {
            Dish dish = new Dish();
            dish.setIdMeal(dishModel.getIdMeal());
            dish.setDishName(dishModel.getDishName());
            dish.setIdDishType(dishModel.getDishType().getDishTypeValue());
            dish.setIdRecipe(dishModel.getIdRecipe());
            dish.setOrderSequence(dishModel.getOrderSequence());
            dish.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            dish = dishRepository.save(dish);
            if (dish != null && dish.getIdDish() != null) {
                dishModel.setIdDish(dish.getIdDish());
                dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error adding the dish to the meal with id {}: ", dishModel.getIdMeal(), e);
            dishModel.setIdDish(null);
            dishModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishModel.setMessage("There was an error adding dish to the meal.");
        }
        return dishModel;
    }

    @Transactional
    public BaseModel removeDish(Integer idMeal, Integer idDish) {
        BaseModel baseModel = new BaseModel();
        //TODO:  Cascade delete
        try {
            dishRepository.deleteByIdMealAndIdDish(idMeal, idDish);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error removing the dish ({}) for meal ({}): ", idDish, idMeal, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the dish from the meal.");
        }
        return baseModel;
    }

    @Transactional
    public DishModel updateDish(DishModel dishModel) {
        try {
            Dish dish = dishRepository.findByIdMealAndIdDish(dishModel.getIdMeal(), dishModel.getIdDish());
            if (StringUtils.isNotBlank(dishModel.getDishName())) {
                dish.setDishName(dishModel.getDishName());
            }
            if (dishModel.getDishType() != null) {
                dish.setIdDishType(dishModel.getDishType().getDishTypeValue());
            }
            if (dishModel.getIdRecipe() != null) {
                dish.setIdRecipe(dishModel.getIdRecipe());
            }
            if (dishModel.getOrderSequence() != 0) {
                dish.setOrderSequence(dishModel.getOrderSequence());
            }
            dish.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            dish = dishRepository.save(dish);
            if (dish != null && dish.getIdDish() != null) {
                dishModel.setIdDish(dish.getIdDish());
                dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error updating the dish for meal ({}): ", dishModel.getIdMeal(), e);
            dishModel.setIdDish(null);
            dishModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishModel.setMessage("There was an error updating the dish.");
        }
        return dishModel;
    }

    public DishModel getDish(Integer idMeal, Integer idDish) {
        DishModel dishModel = new DishModel();
        try {
            Dish dish = dishRepository.findByIdMealAndIdDish(idMeal, idDish);
            dishModel.setIdMeal(dish.getIdMeal());
            dishModel.setIdRecipe(dish.getIdRecipe());
            dishModel.setOrderSequence(dish.getOrderSequence());
            dishModel.setCreateTimestamp(dish.getCreateTimestamp());
            dishModel.setUpdateTimestamp(dish.getUpdateTimestamp());
            dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error retrieving the dish ({}) for meal ({}): ", idDish, idMeal, e);
            dishModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishModel.setMessage("There was an error retrieving the dish.");
        }
        return dishModel;
    }

    public DishListModel getDishList(Integer idMeal) {
        DishListModel dishListModel = new DishListModel();
        try {
            List<Dish> dishList = dishRepository.findByIdMealOrderByOrderSequenceDesc(idMeal);
            if (CollectionUtils.isNotEmpty(dishList)) {
                List<DishModel> dishModelList = new ArrayList<>();
                for (Dish dish : dishList) {
                    DishModel dishModel = new DishModel();
                    dishModel.setIdMeal(dish.getIdMeal());
                    dishModel.setIdDish(dish.getIdDish());
                    dishModel.setIdRecipe(dish.getIdRecipe());
                    dishModel.setDishName(dish.getDishName());
                    dishModel.setDishType(DishTypeEnum.get(dish.getIdDishType()));
                    dishModel.setOrderSequence(dish.getOrderSequence());
                    dishModel.setCreateTimestamp(dish.getCreateTimestamp());
                    dishModel.setUpdateTimestamp(dish.getUpdateTimestamp());
                    dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
                    dishModelList.add(dishModel);
                }
                dishListModel.setDishModelList(dishModelList);
                dishListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("There were no dishes found for meal ({}): ", idMeal);
                dishListModel.setDishModelList(new ArrayList<>());
                dishListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                dishListModel.setMessage("There were no dishes found for this meal.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving the dishes for meal {}: ", idMeal, e);
            dishListModel.setDishModelList(new ArrayList<>());
            dishListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishListModel.setMessage("There was an error retrieving the dish list.");
        }
        return dishListModel;
    }
}
