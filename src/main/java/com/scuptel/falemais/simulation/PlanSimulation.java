package com.scuptel.falemais.simulation;

import com.scuptel.falemais.dto.SimulationDTO;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.model.Plan;
import com.sun.management.VMOption;

import java.math.BigDecimal;

public interface PlanSimulation {

    BigDecimal simulate(SimulationDTO simulationDTO);

}
