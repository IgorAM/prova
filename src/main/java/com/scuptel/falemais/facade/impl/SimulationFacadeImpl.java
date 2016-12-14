package com.scuptel.falemais.facade.impl;

import com.scuptel.falemais.dto.SimulationDTO;
import com.scuptel.falemais.dto.SimulationDetailedDTO;
import com.scuptel.falemais.dto.SimulationRequestDTO;
import com.scuptel.falemais.dto.SimulationResponseDTO;
import com.scuptel.falemais.facade.SimulationFacade;
import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.service.CallTaxService;
import com.scuptel.falemais.service.CityCodeService;
import com.scuptel.falemais.service.PlanService;
import com.scuptel.falemais.service.impl.CallTaxServiceImpl;
import com.scuptel.falemais.service.impl.CityCodeServiceImpl;
import com.scuptel.falemais.service.impl.PlanServiceImpl;
import com.scuptel.falemais.simulation.FaleMaisPlanSimulation;
import com.scuptel.falemais.simulation.NoPlanSimulation;
import com.scuptel.falemais.simulation.PlanSimulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component("simulationFacadeImpl")
public class SimulationFacadeImpl implements SimulationFacade {

    @Autowired
    @Qualifier("cityCodeServiceImpl")
    private CityCodeService cityCodeService;

    @Autowired
    @Qualifier("planServiceImpl")
    private PlanService planService;

    @Autowired
    @Qualifier("callTaxServiceImpl")
    private CallTaxService callTaxService;

    public SimulationResponseDTO simulate(SimulationRequestDTO dto){

        final Optional<CityCode> origin = Optional.ofNullable(cityCodeService.findById(Long.valueOf(dto.getOriginCity())));
        origin.orElseThrow(() -> new IllegalArgumentException("Origin City Code not found"));

        final Optional<CityCode> destination = Optional.ofNullable(cityCodeService.findById(Long.valueOf(dto.getDestinationCity())));
        destination.orElseThrow(() -> new IllegalArgumentException("Destination City Code not found"));

        final Optional<Plan> plan = Optional.ofNullable(planService.findById(Long.valueOf(dto.getPlan())));
        plan.orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        final CallTax callTax = callTaxService.findCallTax(origin.get(), destination.get());

        if(callTax == null){
            return new SimulationResponseDTO(HttpStatus.NO_CONTENT.value());
        }

        SimulationDTO simulationDto = new SimulationDTO(callTax, plan.get(), dto.getCallTimeAsInteger());

        PlanSimulation noPlanSimulation = new NoPlanSimulation();
        final BigDecimal priceWithoutPlan = noPlanSimulation.simulate(simulationDto);

        PlanSimulation faleMaisPlanSimulation = new FaleMaisPlanSimulation();
        final BigDecimal priceFaleMaisPlan = faleMaisPlanSimulation.simulate(simulationDto);

        return new SimulationResponseDTO(HttpStatus.OK.value(),
                new SimulationDetailedDTO(plan.get().getName(), priceFaleMaisPlan.floatValue()),
                new SimulationDetailedDTO(priceWithoutPlan.floatValue()));

    }

}
