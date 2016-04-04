package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.dish.Dish;
import com.debes.plateplanner.dao.dish.repository.DishRepository;
import com.debes.plateplanner.dao.meal.Meal;
import com.debes.plateplanner.dao.meal.MealType;
import com.debes.plateplanner.dao.meal.repository.MealRepository;
import com.debes.plateplanner.dao.meal.repository.MealTypeRepository;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishListModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.enums.DishTypeEnum;
import com.debes.plateplanner.models.enums.MealTypeEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.meal.MealListModel;
import com.debes.plateplanner.models.meal.MealModel;
import com.debes.plateplanner.models.meal.MealTypeListModel;
import com.debes.plateplanner.models.meal.MealTypeModel;
import com.debes.plateplanner.util.DateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
            if (meal.getMealDate() != null) {
                mealModel.setMealDate(DateTimeUtil.format(meal.getMealDate().toLocalDate()));
            }
            mealModel.setMealType(MealTypeEnum.get(meal.getIdMealType()));
            mealModel.setOrderSequence(meal.getOrderSequence());
            if (meal.getCreateTimestamp() != null) {
                mealModel.setCreateTimestamp(DateTimeUtil.format(meal.getCreateTimestamp().toLocalDateTime()));
            }
            if (meal.getUpdateTimestamp() != null) {
                mealModel.setUpdateTimestamp(DateTimeUtil.format(meal.getUpdateTimestamp().toLocalDateTime()));
            }
            mealModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error retrieving meal with id {}: ", idMeal, e);
            mealModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            mealModel.setMessage("There was an error retrieving the meal.");
        }
        return mealModel;
    }

    public MealListModel getMeals() {
        MealListModel mealListModel = new MealListModel();
        List<Meal> mealList = mealRepository.findAll();
        try {
            if (CollectionUtils.isNotEmpty(mealList)) {
                List<MealModel> mealModelList = new ArrayList<>();
                for (Meal meal: mealList) {
                    MealModel mealModel = new MealModel();
                    mealModel.setIdMeal(meal.getIdMeal());
                    mealModel.setMealName(meal.getMealName());
                    mealModel.setOrderSequence(meal.getOrderSequence());
                    mealModel.setMealType(MealTypeEnum.get(meal.getIdMealType()));
                    if (meal.getMealDate() != null) {
                        mealModel.setMealDate(DateTimeUtil.format(meal.getMealDate().toLocalDate()));
                    }
                    if (meal.getCreateTimestamp() != null) {
                        mealModel.setCreateTimestamp(DateTimeUtil.format(meal.getCreateTimestamp().toLocalDateTime()));
                    }
                    if (meal.getUpdateTimestamp() != null) {
                        mealModel.setUpdateTimestamp(DateTimeUtil.format(meal.getUpdateTimestamp().toLocalDateTime()));
                    }
                    mealModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
                    mealModelList.add(mealModel);
                }
                mealListModel.setMealList(mealModelList);
            } else {
                logger.error("No meals found.");
                mealListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                mealListModel.setMealList(new ArrayList<>());
                mealListModel.setMessage("No meals found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving the meals: ", e);
            mealListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            mealListModel.setMealList(new ArrayList<>());
            mealListModel.setMessage("There was an error retrieving the meals.");
        }
        return mealListModel;
    }

    public MealTypeListModel getMealTypes() {
        MealTypeListModel mealTypeListModel = new MealTypeListModel();
        try {
            List<MealType> mealTypeList = mealTypeRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(mealTypeList)) {
                List<MealTypeModel> mealTypeModelList = new ArrayList<>();
                for (MealType mealType : mealTypeList) {
                    MealTypeModel mealTypeModel = new MealTypeModel();
                    mealTypeModel.setMealType(MealTypeEnum.get(mealType.getIdMealType()));
                    mealTypeModel.setMealTypeDescription(mealType.getMealType());
                    mealTypeModelList.add(mealTypeModel);
                }
                mealTypeListModel.setMealTypeList(mealTypeModelList);
                mealTypeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No meal types found.");
                mealTypeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                mealTypeListModel.setMealTypeList(new ArrayList<>());
                mealTypeListModel.setMessage("No meal types found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving meal types: ", e);
            mealTypeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            mealTypeListModel.setMealTypeList(new ArrayList<>());
            mealTypeListModel.setMessage("There was an error retrieving the list of meal types.");
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
            meal.setIdMealType(mealModel.getMealType().getMealTypeValue());
            meal.setOrderSequence(mealModel.getOrderSequence());
            meal = mealRepository.save(meal);
            if (meal != null && meal.getIdMeal() != null) {
                mealModel.setIdMeal(meal.getIdMeal());
                mealModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error upserting the meal: ", e);
            mealModel.setIdMeal(null);
            mealModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            mealModel.setMessage("There was an error saving the meal.");
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
            logger.error("There was an error removing the meal with id {}: ", idMeal, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the meal.");
        }
        return baseModel;
    }

    @Transactional
    public DishModel addDish(Integer idMeal, DishModel dishModel) {
        try {
            Dish dish = new Dish();
            dish.setIdMeal(idMeal);
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
            logger.error("There was an error adding the dish to the meal with id {}: ", idMeal, e);
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
    public DishModel updateDish(Integer idMeal, DishModel dishModel) {
        try {
            Dish dish = dishRepository.findByIdMealAndIdDish(idMeal, dishModel.getIdDish());
            dish.setDishName(dishModel.getDishName());
            dish.setIdDishType(dishModel.getDishType().getDishTypeValue());
            dish.setIdRecipe(dishModel.getIdRecipe());
            dish.setOrderSequence(dishModel.getOrderSequence());
            dish.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            dish = dishRepository.save(dish);
            if (dish != null && dish.getIdDish() != null) {
                dishModel.setIdDish(dish.getIdDish());
                dishModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error updating the dish for meal ({}): ", idMeal, e);
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
            if (dishModel.getCreateTimestamp() != null) {
                dishModel.setCreateTimestamp(DateTimeUtil.format(dish.getCreateTimestamp().toLocalDateTime()));
            }
            dishModel.setCreateTimestamp(DateTimeUtil.format(dish.getCreateTimestamp().toLocalDateTime()));
            if (dish.getUpdateTimestamp() != null) {
                dishModel.setUpdateTimestamp(DateTimeUtil.format(dish.getUpdateTimestamp().toLocalDateTime()));
            }
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
                    if (dish.getCreateTimestamp() != null) {
                        dishModel.setCreateTimestamp(DateTimeUtil.format(dish.getCreateTimestamp().toLocalDateTime()));
                    }
                    if (dish.getUpdateTimestamp() != null) {
                        dishModel.setUpdateTimestamp(DateTimeUtil.format(dish.getUpdateTimestamp().toLocalDateTime()));
                    }
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
