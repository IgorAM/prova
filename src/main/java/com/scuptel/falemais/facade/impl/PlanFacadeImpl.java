package com.scuptel.falemais.facade.impl;

import com.scuptel.falemais.assembler.PlanAssembler;
import com.scuptel.falemais.dto.PlanListDTO;
import com.scuptel.falemais.facade.PlanFacade;
import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("planFacadeImpl")
public class PlanFacadeImpl implements PlanFacade {

    @Autowired
    @Qualifier("planServiceImpl")
    private PlanService planService;

    @Autowired
    private PlanAssembler planAssembler;

    public PlanListDTO findAll(){
        List<Plan> plans = planService.findAll();
        return planAssembler.toDTOList(plans);
    }

}
