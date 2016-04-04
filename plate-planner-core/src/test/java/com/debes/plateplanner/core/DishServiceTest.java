package com.debes.plateplanner.core;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.dish.DishListModel;
import com.debes.plateplanner.models.dish.DishModel;
import com.debes.plateplanner.models.dish.DishTypeListModel;
import com.debes.plateplanner.models.enums.DishTypeEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author lesley.debes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@ContextConfiguration("classpath:plate-planner-test-core.xml")
public class DishServiceTest {
    @Autowired
    private DishService dishService;

    @Test
    public void test_getDishTypeList() {
        DishTypeListModel dishTypeListModel = dishService.getDishTypes();
        assertNotNull(dishTypeListModel);
        assertEquals(ModelStatusEnum.SUCCESS, dishTypeListModel.getModelStatusEnum());
        assertEquals(7, CollectionUtils.size(dishTypeListModel.getDishTypeList()));
    }

    @Test
    @Transactional
    public void test_addDish() {
        DishModel dishModel = new DishModel();
        dishModel.setIdMeal(3);
        dishModel.setIdRecipe(2);
        dishModel.setDishName("Ziti");
        dishModel.setDishType(DishTypeEnum.ENTREE);
        dishModel.setOrderSequence((short) 1);
        dishModel = dishService.addDish(dishModel);
        assertNotNull(dishModel);
        assertEquals(ModelStatusEnum.SUCCESS, dishModel.getModelStatusEnum());
    }

    @Test
    @Transactional
    public void test_updateDish() {
        DishModel dishModel = new DishModel();
        dishModel.setIdDish(1);
        dishModel.setIdMeal(3);
        dishModel.setDishName("Baked Ziti");
        dishModel.setDishType(DishTypeEnum.ENTREE);
        dishModel.setIdRecipe(2);
        dishModel.setOrderSequence((short)2);
        dishModel = dishService.updateDish(dishModel);
        assertNotNull(dishModel);
        assertEquals(ModelStatusEnum.SUCCESS, dishModel.getModelStatusEnum());
    }

    @Test
    @Transactional
    public void test_removeDish() {
        BaseModel baseModel = dishService.removeDish(3, 1);
        assertNotNull(baseModel);
        assertEquals(ModelStatusEnum.SUCCESS, baseModel.getModelStatusEnum());
    }

    @Test
    public void test_getDish() {
        DishModel dishModel = dishService.getDish(3, 1);
        assertNotNull(dishModel);
        assertEquals(ModelStatusEnum.SUCCESS, dishModel.getModelStatusEnum());
    }

    @Test
    public void test_getDishList() {
        DishListModel dishListModel = dishService.getDishList(3);
        assertNotNull(dishListModel);
        assertEquals(ModelStatusEnum.SUCCESS, dishListModel.getModelStatusEnum());
        assertTrue(CollectionUtils.isNotEmpty(dishListModel.getDishModelList()));
        assertTrue(dishListModel.getDishModelList()
                .stream()
                .allMatch(d -> ModelStatusEnum.SUCCESS.equals(d.getModelStatusEnum())));
    }
}
