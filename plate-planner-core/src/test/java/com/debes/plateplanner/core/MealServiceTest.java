package com.debes.plateplanner.core;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MealTypeEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.meal.MealListModel;
import com.debes.plateplanner.models.meal.MealModel;
import com.debes.plateplanner.models.meal.MealTypeListModel;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author lesley.debes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ContextConfiguration("classpath:plate-planner-test-core.xml")
public class MealServiceTest {
    @Autowired
    private MealService mealService;

    @Test
    @Transactional
    public void test_addMeal_updateMeal_Success() {
        MealModel mealModel = new MealModel();
        mealModel.setMealName("Breakfast - Wednesday");
        LocalDate localDate = LocalDate.of(2016, 4, 5);
        mealModel.setMealDate(Date.valueOf(localDate));
        mealModel.setMealType(MealTypeEnum.BREAKFAST);
        mealModel.setOrderSequence((short)1);

        mealModel = mealService.addMeal(mealModel);
        assertNotNull(mealModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealModel.getModelStatusEnum());
        assertNotNull(mealModel.getIdMeal());

        Integer idMeal = mealModel.getIdMeal();
        mealModel.setMealName("Breakfast: Wednesday");
        mealModel = mealService.updateMeal(idMeal, mealModel);
        assertNotNull(mealModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealModel.getModelStatusEnum());
        assertNotNull(mealModel.getIdMeal());
        assertEquals(idMeal, mealModel.getIdMeal());
    }

    @Test
    public void test_getMeal() {
        MealModel mealModel = mealService.getMeal(3);
        assertNotNull(mealModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealModel.getModelStatusEnum());
        assertEquals(3, mealModel.getIdMeal(), 0);
    }

    @Test
    @Transactional
    public void test_removeMeal_Success() {
        BaseModel baseModel = mealService.removeMeal(3);
        assertNotNull(baseModel);
        assertEquals(ModelStatusEnum.SUCCESS, baseModel.getModelStatusEnum());
    }

    @Test
    @Transactional
    public void test_removeMeal_Failure() {
        BaseModel baseModel = mealService.removeMeal(0);
        assertNotNull(baseModel);
        assertEquals(ModelStatusEnum.ERROR, baseModel.getModelStatusEnum());
        assertEquals("There was an error removing the meal.", baseModel.getMessage());
    }

    @Test
    public void test_getMeals() {
        MealListModel mealListModel = mealService.getMeals();
        assertNotNull(mealListModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealListModel.getModelStatusEnum());
        assertTrue(CollectionUtils.isNotEmpty(mealListModel.getMealList()));
        assertTrue(mealListModel.getMealList()
                .stream()
                .allMatch(m -> ModelStatusEnum.SUCCESS.equals(m.getModelStatusEnum())));
    }

    @Test
    public void test_getMealTypeList() {
        MealTypeListModel mealTypeListModel = mealService.getMealTypes();
        assertNotNull(mealTypeListModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealTypeListModel.getModelStatusEnum());
        assertEquals(3, CollectionUtils.size(mealTypeListModel.getMealTypeList()));
    }
}
