package com.scuptel.falemais.dto;

import com.scuptel.falemais.util.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

public class SimulationRequestDTO {

    private String originCity;

    private String destinationCity;

    private String callTime;

    private String plan;

    public String getCallTime() {
        return callTime;
    }

    public Integer getCallTimeAsInteger() {
        return new Integer(callTime);
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public SimulationResponseDTO checkData(){
        SimulationResponseDTO response = new SimulationResponseDTO();

        if(hasEmptyData(response)){
            response.setCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }

        if(hasInvalidData(response)){
            response.setCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }

        return response;
    }

    private boolean hasEmptyData(SimulationResponseDTO response){

        boolean isEmpty = false;

        if(StringUtils.isBlank(getCallTime())){
            response.addErrorMessage("validation.simulation.calltime.empty");
            isEmpty = true;
        }

        if(StringUtils.isBlank(getOriginCity())){
            response.addErrorMessage("validation.simulation.origin.city.empty");
            isEmpty = true;
        }

        if(StringUtils.isBlank(getDestinationCity())){
            response.addErrorMessage("validation.simulation.destination.city.empty");
            isEmpty = true;
        }

        if(StringUtils.isBlank(getPlan())){
            response.addErrorMessage("validation.simulation.plan.empty");
            isEmpty = true;
        }

        return isEmpty;
    }

    private boolean hasInvalidData(SimulationResponseDTO response){
        boolean isInvalid = false;

        if(!StringUtils.isNumeric(getCallTime())){
            response.addErrorMessage("validation.simulation.calltime.invalid.format");
            isInvalid = true;
        }else if(new Integer(getCallTime()) <= 0){
            response.addErrorMessage("validation.simulation.calltime.invalid");
            isInvalid = true;
        }

        return isInvalid;
    }
}
