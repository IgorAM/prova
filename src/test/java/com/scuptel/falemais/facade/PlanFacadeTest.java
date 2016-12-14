package com.scuptel.falemais.facade;

import com.scuptel.falemais.assembler.PlanAssembler;
import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.dto.PlanDTO;
import com.scuptel.falemais.dto.PlanListDTO;
import com.scuptel.falemais.facade.impl.PlanFacadeImpl;
import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.service.impl.PlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class PlanFacadeTest {

    @InjectMocks
    private PlanFacadeImpl planFacade;

    @Mock
    private PlanServiceImpl planService;

    @Mock
    private PlanAssembler planAssembler;

    @Test
    public void no_plan_found_finding_all(){

        PlanListDTO expectedPlanListDTO = new PlanListDTO();

        when(planService.findAll()).thenReturn(null);
        when(planAssembler.toDTOList(null)).thenReturn(expectedPlanListDTO);

        PlanListDTO planListDTO = planFacade.findAll();

        assertTrue(planListDTO.equals(expectedPlanListDTO));
    }

    @Test
    public void plans_found_finding_all(){

        Plan expectedPlan = new Plan(1l, new BigDecimal(10), 30, "FaleMais 30");
        List<Plan> expectedPlans = Collections.singletonList(expectedPlan);

        when(planService.findAll()).thenReturn(expectedPlans);


        PlanDTO expectedPlanDTO = new PlanDTO("1", "FaleMais 30");

        PlanListDTO expectedPlanListDTO = new PlanListDTO();
        expectedPlanListDTO.add(expectedPlanDTO);

        when(planAssembler.toDTOList(expectedPlans)).thenReturn(expectedPlanListDTO);


        PlanListDTO planListDTO = planFacade.findAll();

        assertNotNull(planListDTO);
        assertNotNull(planListDTO.getList());
        assertEquals(planListDTO.getList().get(0), expectedPlanListDTO.getList().get(0));
        assertEquals(planListDTO, expectedPlanListDTO);
    }

}
