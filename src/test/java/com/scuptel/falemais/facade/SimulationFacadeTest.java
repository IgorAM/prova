package com.scuptel.falemais.facade;

import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.dto.SimulationDetailedDTO;
import com.scuptel.falemais.dto.SimulationRequestDTO;
import com.scuptel.falemais.dto.SimulationResponseDTO;
import com.scuptel.falemais.facade.impl.SimulationFacadeImpl;
import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.service.CallTaxService;
import com.scuptel.falemais.service.CityCodeService;
import com.scuptel.falemais.service.PlanService;
import com.scuptel.falemais.service.impl.CallTaxServiceImpl;
import com.scuptel.falemais.service.impl.CityCodeServiceImpl;
import com.scuptel.falemais.service.impl.PlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class SimulationFacadeTest {

    @Mock
    private CityCodeService cityCodeService;

    @Mock
    private PlanService planService;

    @Mock
    private CallTaxService callTaxService;

    @InjectMocks
    private SimulationFacadeImpl simulationFacade;

    @Test(expected = IllegalArgumentException.class)
    public void invalid_origin_city_code(){
        when(cityCodeService.findById(0l)).thenThrow(IllegalArgumentException.class);

        cityCodeService.findById(0l);

    }

    @Test(expected = IllegalArgumentException.class)
    public void invalid_plan(){
        when((planService.findById(0l))).thenThrow(IllegalArgumentException.class);
        planService.findById(0l);
    }

    @Test
    public void call_tax_not_found(){

        CityCode originCityCode = new CityCode(100l, 21);
        CityCode destinationCityCode = new CityCode(101l, 24);

        Plan plan = new Plan(1l, new BigDecimal(10), 50, "FaleMais 50");

        SimulationRequestDTO dto = new SimulationRequestDTO();
        dto.setOriginCity("100");
        dto.setDestinationCity("101");
        dto.setPlan("1");

        when(cityCodeService.findById(Long.valueOf(dto.getOriginCity()))).thenReturn(originCityCode);
        when(cityCodeService.findById(Long.valueOf(dto.getDestinationCity()))).thenReturn(destinationCityCode);
        when(planService.findById(Long.valueOf(dto.getPlan()))).thenReturn(plan);
        when(callTaxService.findCallTax(originCityCode, destinationCityCode)).thenReturn(null);

        SimulationResponseDTO expectedSimulationResponseDTO = new SimulationResponseDTO(HttpStatus.NO_CONTENT.value());

        SimulationResponseDTO simulationResponseDTO = simulationFacade.simulate(dto);

        assertEquals(expectedSimulationResponseDTO, simulationResponseDTO);

    }

    @Test
    public void success_falemais_price_zero(){

        CityCode originCityCode = new CityCode(100l, 11);
        CityCode destinationCityCode = new CityCode(101l, 16);

        Plan plan = new Plan(1l, new BigDecimal(10), 30, "FaleMais 30");

        SimulationRequestDTO dto = new SimulationRequestDTO();
        dto.setOriginCity("100");
        dto.setDestinationCity("101");
        dto.setPlan("1");
        dto.setCallTime("20");

        CallTax callTax = new CallTax(originCityCode, destinationCityCode, new BigDecimal(1.90));

        when(cityCodeService.findById(Long.valueOf(dto.getOriginCity()))).thenReturn(originCityCode);
        when(cityCodeService.findById(Long.valueOf(dto.getDestinationCity()))).thenReturn(destinationCityCode);
        when(planService.findById(Long.valueOf(dto.getPlan()))).thenReturn(plan);
        when(callTaxService.findCallTax(originCityCode, destinationCityCode)).thenReturn(callTax);

        SimulationResponseDTO expectedSimulationResponseDTO = new SimulationResponseDTO(HttpStatus.OK.value(),
                new SimulationDetailedDTO(plan.getName(), 0f),
                new SimulationDetailedDTO(38));

        SimulationResponseDTO responseDTO = simulationFacade.simulate(dto);

        assertTrue(expectedSimulationResponseDTO.equals(responseDTO));

    }


    @Test
    public void success_falemais_with_price(){

        CityCode originCityCode = new CityCode(100l, 11);
        CityCode destinationCityCode = new CityCode(101l, 17);

        Plan plan = new Plan(1l, new BigDecimal(10), 60, "FaleMais 60");

        SimulationRequestDTO dto = new SimulationRequestDTO();
        dto.setOriginCity("100");
        dto.setDestinationCity("101");
        dto.setPlan("1");
        dto.setCallTime("80");

        CallTax callTax = new CallTax(originCityCode, destinationCityCode, new BigDecimal(1.70));

        when(cityCodeService.findById(Long.valueOf(dto.getOriginCity()))).thenReturn(originCityCode);
        when(cityCodeService.findById(Long.valueOf(dto.getDestinationCity()))).thenReturn(destinationCityCode);
        when(planService.findById(Long.valueOf(dto.getPlan()))).thenReturn(plan);
        when(callTaxService.findCallTax(originCityCode, destinationCityCode)).thenReturn(callTax);

        SimulationResponseDTO expectedSimulationResponseDTO = new SimulationResponseDTO(HttpStatus.OK.value(),
                new SimulationDetailedDTO(plan.getName(), 37.4f),
                new SimulationDetailedDTO(136));

        SimulationResponseDTO responseDTO = simulationFacade.simulate(dto);

        assertTrue(expectedSimulationResponseDTO.equals(responseDTO));

    }
}
