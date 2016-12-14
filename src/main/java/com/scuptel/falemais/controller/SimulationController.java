package com.scuptel.falemais.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.scuptel.falemais.dto.*;
import com.scuptel.falemais.facade.CityCodeFacade;
import com.scuptel.falemais.facade.PlanFacade;
import com.scuptel.falemais.facade.SimulationFacade;
import com.scuptel.falemais.dto.view.Visibility;
import com.scuptel.falemais.util.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/simulation"})
public class SimulationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationController.class);

    @Autowired
    @Qualifier("cityCodeFacadeImpl")
    private CityCodeFacade cityCodeFacade;

    @Autowired
    @Qualifier("planFacadeImpl")
    private PlanFacade planFacade;

    @Autowired
    @Qualifier("simulationFacadeImpl")
    private SimulationFacade simulationFacade;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUsersView() {
        ModelAndView mv= new ModelAndView("call_price_simulator");
        return mv;
    }

    @ModelAttribute("simulationForm")
    public SimulationRequestDTO simulationForm(){
        return new SimulationRequestDTO();
    }

    @ModelAttribute("cityCodes")
    public List<CityCodeDTO> cityCodes(){
        return cityCodeFacade.findAll().getList();
    }

    @ModelAttribute("plans")
    public List<PlanDTO> plans(){
        return planFacade.findAll().getList();
    }

    @ResponseBody
    @JsonView(Visibility.Public.class)
    @RequestMapping(value =  "/simulation", method = RequestMethod.POST)
    public SimulationResponseDTO simulate(@RequestBody SimulationRequestDTO simulationRequestDTO) {

        final SimulationResponseDTO response = simulationRequestDTO.checkData();

        if(response.hasErrors()){
            return replaceMessageCodeToText(response);
        }

        try{
            return simulationFacade.simulate(simulationRequestDTO);
        }catch(IllegalArgumentException illegalException){
            LOGGER.error(illegalException.getMessage());
            SimulationResponseDTO error = new SimulationResponseDTO();
            error.setCode(HttpStatus.BAD_REQUEST.value());
            error.addErrorMessage("validation.simulation.invalid.parameters");
            return replaceMessageCodeToText(error);
        }

    }

    @ResponseBody
    @JsonView(Visibility.Public.class)
    @RequestMapping(path = "/destinations_city_code")
    public CityCodeResponseDTO getDestinations(@RequestBody CityCodeRequestDTO cityCodeRequestDTO){

        List<CityCodeDTO> destinations = cityCodeFacade.destinationsByOrigin(cityCodeRequestDTO.getOriginCity()).getList();

        if(CollectionUtils.isEmpty(destinations)){
            return new CityCodeResponseDTO(400);
        }

        return new CityCodeResponseDTO(200, destinations);
    }

    private SimulationResponseDTO replaceMessageCodeToText(SimulationResponseDTO response){
        response.setErrorMessages(messageUtil.getMessage(response.getErrorMessages()));
        return response;
    }

}