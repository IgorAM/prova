package com.scuptel.falemais.assembler;

import com.scuptel.falemais.dto.PlanDTO;
import com.scuptel.falemais.dto.PlanListDTO;
import com.scuptel.falemais.model.Plan;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanAssembler {

    public PlanDTO toDTO(Plan plan){
        return new PlanDTO(String.valueOf(plan.getId()), String.valueOf(plan.getName()));
    }

    public PlanListDTO toDTOList(List<Plan> plans){
        if(CollectionUtils.isEmpty(plans)){
            return new PlanListDTO();
        }

        PlanListDTO dtos = new PlanListDTO();

        for(Plan plan : plans){
            dtos.add(toDTO(plan));
        }

        return dtos;
    }
}
