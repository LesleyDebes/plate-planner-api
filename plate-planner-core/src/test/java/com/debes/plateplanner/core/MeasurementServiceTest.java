package com.debes.plateplanner.core;

import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.MeasurementListModel;
import org.apache.commons.collections4.CollectionUtils;
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
public class MeasurementServiceTest {
    @Autowired
    private MeasurementService measurementService;

    @Test
    public void test_getMeasurements() {
        MeasurementListModel measurementListModel = measurementService.getMeasurements();
        assertNotNull(measurementListModel);
        assertEquals(ModelStatusEnum.SUCCESS, measurementListModel.getModelStatusEnum());
        assertEquals(12, CollectionUtils.size(measurementListModel.getMeasurementList()));
    }
}
