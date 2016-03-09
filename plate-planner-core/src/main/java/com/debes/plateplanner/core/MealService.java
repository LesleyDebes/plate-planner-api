package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.dish.Dish;
import com.debes.plateplanner.dao.dish.repository.DishRepository;
import com.debes.plateplanner.dao.meal.Meal;
import com.debes.plateplanner.dao.meal.MealType;
import com.debes.plateplanner.dao.meal.repository.MealRepository;
import com.debes.plateplanner.dao.meal.repository.MealTypeRepository;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.meal.MealModel;
import com.debes.plateplanner.models.meal.MealTypeListModel;
import com.debes.plateplanner.models.meal.MealTypeModel;
import com.debes.plateplanner.util.DateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lesley.debes
 */
@Service
public class MealService {
    //TODO:  ADD LOGGER

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @Autowired
    private DishRepository dishRepository;

    public MealModel getMeal(Integer idMeal) {
        MealModel mealModel = new MealModel();
        try {
            Meal meal = mealRepository.findOne(idMeal);
            mealModel.setIdMeal(meal.getIdMeal());
            mealModel.setMealName(meal.getMealName());
            mealModel.setMealDate(DateTimeUtil.format(meal.getMealDate().toLocalDate()));
            mealModel.setIdMealType(meal.getIdMealType());
            mealModel.setOrderSequence(meal.getOrderSequence());
            mealModel.setCreateTimestamp(DateTimeUtil.format(meal.getCreateTimestamp().toLocalDateTime()));
            if (meal.getUpdateTimestamp() != null) {
                mealModel.setUpdateTimestamp(DateTimeUtil.format(meal.getUpdateTimestamp().toLocalDateTime()));
            }
            mealModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            mealModel.setMessage("There was an error retrieving meal with id: " + idMeal);
            //TODO:  LOGGER STATEMENT
        }
        return mealModel;
    }

    public MealTypeListModel getMealTypes() {
        MealTypeListModel mealTypeListModel = new MealTypeListModel();
        try {
            List<MealType> mealTypeList = mealTypeRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(mealTypeList)) {
                //TODO:  Use Lambdas?
                List<MealTypeModel> mealTypeModelList = new ArrayList<>();
                for (MealType mealType : mealTypeList) {
                    MealTypeModel mealTypeModel = new MealTypeModel();
                    mealTypeModel.setIdMealType(mealType.getIdMealType());
                    mealTypeModel.setMealType(mealType.getMealType());
                    mealTypeModelList.add(mealTypeModel);
                }
                mealTypeListModel.setMealTypeList(mealTypeModelList);
                mealTypeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                //TODO:  HOW TO THROW NO DATA FOUND EXCEPTION
                mealTypeListModel.setMealTypeList(new ArrayList<>());
                mealTypeListModel.setMessage("There was an error retrieving meal types");
            }
        } catch (Exception e) {
            mealTypeListModel.setMealTypeList(new ArrayList<>());
            mealTypeListModel.setMessage("There was an error retrieving meal types");
            //TODO:  LOGGER STATEMENT
        }

        return mealTypeListModel;
    }

    @Transactional
    public MealModel upsertMeal(MealModel mealModel) {
        try {
            Meal meal;
            if (mealModel.getIdMeal() == null) {
                // Create a new meal record
                meal = new Meal();
                meal.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            } else {
                // Use the existing meal record
                meal = mealRepository.findOne(mealModel.getIdMeal());
                meal.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            }
            meal.setMealName(mealModel.getMealName());
            meal.setMealDate(Date.valueOf(DateTimeUtil.parseDate(mealModel.getMealDate())));
            meal.setIdMealType(mealModel.getIdMealType());
            meal.setOrderSequence(mealModel.getOrderSequence());
            meal = mealRepository.save(meal);
            if (meal != null && meal.getIdMeal() != null) {
                mealModel.setIdMeal(meal.getIdMeal());
                mealModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            mealModel.setIdMeal(null);
            mealModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            mealModel.setMessage("There was an error upserting meal");
            //TODO:  LOGGER STATEMENT
        }
        return mealModel;
    }

    @Transactional
    public BaseModel removeMeal(Integer idMeal) {
        BaseModel baseModel = new BaseModel();
        //TODO:  Cascade delete
        try {
            mealRepository.delete(idMeal);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing meal: " + idMeal);
        }
        return baseModel;
    }

    @Transactional
    public DishModel addDish(Integer idMeal, DishModel dishModel) {
        try {
            Dish dish = new Dish();
            dish.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            dish.setIdMeal(idMeal);
            dish.setDishName(dishModel.getDishName());
            dish.setIdDishType(dishModel.getIdDishType());
            dish.setIdRecipe(dishModel.getIdRecipe());
            dish.setOrderSequence(dishModel.getOrderSequence());
            dish = dishRepository.save(dish);
            if (dish != null && dish.getIdDish() != null) {
                dishModel.setIdDish(dish.getIdDish());
                dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            dishModel.setIdDish(null);
            dishModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            dishModel.setMessage("There was an error adding dish to the meal");
            //TODO:  LOGGER STATEMENT
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
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing meal: " + idMeal);
        }
        return baseModel;
    }


}
