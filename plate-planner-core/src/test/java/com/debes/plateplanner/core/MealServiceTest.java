package com.debes.plateplanner.core;

import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.meal.MealModel;
import com.debes.plateplanner.models.meal.MealTypeListModel;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    @Ignore
    public void test_getMeal() {
        MealModel mealModel = mealService.getMeal(1);
        assertNotNull(mealModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealModel.getModelStatusEnum());
        assertEquals(1, mealModel.getIdMeal(), 0);
    }

    @Test
    public void test_getMealTypeList() {
        MealTypeListModel mealTypeListModel = mealService.getMealTypes();
        assertNotNull(mealTypeListModel);
        assertEquals(ModelStatusEnum.SUCCESS, mealTypeListModel.getModelStatusEnum());
        assertEquals(3, CollectionUtils.size(mealTypeListModel.getMealTypeList()));
    }
}
