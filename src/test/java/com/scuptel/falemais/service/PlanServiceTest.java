package com.scuptel.falemais.service;

import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.repository.PlanRepository;
import com.scuptel.falemais.service.impl.PlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class PlanServiceTest {

    @InjectMocks
    private PlanServiceImpl planService;

    @Mock
    private PlanRepository planRepository;

    @Test
    public void invalid_argument_id(){
        Plan plan = planService.findById(1l);
        assertNull(plan);
    }

    @Test
    public void success_find_one(){
        Plan expectedPlan = new Plan(1l, new BigDecimal(10), 30, "FaleMais 30");
        when(planRepository.findOne(1l)).thenReturn(expectedPlan);
        Plan plan = planService.findById(1l);
        assertTrue(plan.equals(expectedPlan));
    }

}
