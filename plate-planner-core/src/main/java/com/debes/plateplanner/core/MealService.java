package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.meal.Meal;
import com.debes.plateplanner.dao.meal.MealType;
import com.debes.plateplanner.dao.meal.repository.MealRepository;
import com.debes.plateplanner.dao.meal.repository.MealTypeRepository;
import com.debes.plateplanner.models.BaseModel;
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
        //TODO:  Add filters and sorting options
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
                mealListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
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

}
