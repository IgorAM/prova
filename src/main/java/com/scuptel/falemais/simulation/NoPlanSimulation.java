package com.scuptel.falemais.simulation;

import com.scuptel.falemais.dto.SimulationDTO;

import java.math.BigDecimal;

public class NoPlanSimulation implements PlanSimulation {

    @Override
    public BigDecimal simulate(SimulationDTO simulationDTO) {

        return new BigDecimal(simulationDTO.getCallTime()).multiply(simulationDTO.getCallTax().getTaxPerMinute());
    }
}
