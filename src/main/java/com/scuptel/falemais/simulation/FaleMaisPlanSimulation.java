package com.scuptel.falemais.simulation;

import com.scuptel.falemais.dto.SimulationDTO;

import java.math.BigDecimal;

public class FaleMaisPlanSimulation implements PlanSimulation {


    @Override
    public BigDecimal simulate(SimulationDTO simulationDTO) {

        int surplus = simulationDTO.getCallTime() - simulationDTO.getPlan().getFranchise();

        if(surplus <= 0){
            return new BigDecimal(0);
        }

        float taxPerMinute = simulationDTO.getCallTax().getTaxPerMinute().floatValue();
        float additionTax = simulationDTO.getPlan().getAdditionTax().floatValue();

        float additionTaxOvertaxPerMinute = (taxPerMinute * additionTax) / 100f;
        float finalTax = taxPerMinute + additionTaxOvertaxPerMinute;

        return new BigDecimal(surplus).multiply(new BigDecimal(finalTax));

    }
}
