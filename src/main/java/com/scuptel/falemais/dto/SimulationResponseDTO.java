package com.scuptel.falemais.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.scuptel.falemais.dto.view.Visibility;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to return json data in simulation plan.
 */
public class SimulationResponseDTO {

    @JsonView(Visibility.Public.class)
    private List<String> errorMessages;

    @JsonView(Visibility.Public.class)
    private Integer code;

    private boolean hasErrors;

    @JsonView(Visibility.Public.class)
    private SimulationDetailedDTO noPlanDetailed;

    @JsonView(Visibility.Public.class)
    private SimulationDetailedDTO falemaisPlanDetailed;


    public SimulationResponseDTO() {
    }

    public SimulationResponseDTO(Integer code) {
        this.code = code;
    }

    public SimulationResponseDTO(Integer code, SimulationDetailedDTO falemaisPlanDetailed, SimulationDetailedDTO noPlanDetailed) {
        this.code = code;
        this.falemaisPlanDetailed = falemaisPlanDetailed;
        this.noPlanDetailed = noPlanDetailed;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;

        if(getCode() >= 400){
           setHasErrors(true);
        }
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public SimulationDetailedDTO getFalemaisPlanDetailed() {
        return falemaisPlanDetailed;
    }

    public void setFalemaisPlanDetailed(SimulationDetailedDTO falemaisPlanDetailed) {
        this.falemaisPlanDetailed = falemaisPlanDetailed;
    }

    public SimulationDetailedDTO getNoPlanDetailed() {
        return noPlanDetailed;
    }

    public void setNoPlanDetailed(SimulationDetailedDTO noPlanDetailed) {
        this.noPlanDetailed = noPlanDetailed;
    }

    public void addErrorMessage(String msg){
        if(getErrorMessages() == null){
            setErrorMessages(new ArrayList<>());
        }

        getErrorMessages().add(msg);
    }

    @Override
    public String toString() {
        return "SimulationResponseDTO{" +
                "code=" + code +
                ", errorMessages=" + errorMessages +
                ", hasErrors=" + hasErrors +
                ", noPlanDetailed=" + noPlanDetailed +
                ", falemaisPlanDetailed=" + falemaisPlanDetailed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimulationResponseDTO that = (SimulationResponseDTO) o;

        if (hasErrors != that.hasErrors) return false;
        if (errorMessages != null ? !errorMessages.equals(that.errorMessages) : that.errorMessages != null)
            return false;
        if (!code.equals(that.code)) return false;
        if (noPlanDetailed != null ? !noPlanDetailed.equals(that.noPlanDetailed) : that.noPlanDetailed != null)
            return false;
        return !(falemaisPlanDetailed != null ? !falemaisPlanDetailed.equals(that.falemaisPlanDetailed) : that.falemaisPlanDetailed != null);

    }

    @Override
    public int hashCode() {
        int result = errorMessages != null ? errorMessages.hashCode() : 0;
        result = 31 * result + code.hashCode();
        result = 31 * result + (hasErrors ? 1 : 0);
        result = 31 * result + (noPlanDetailed != null ? noPlanDetailed.hashCode() : 0);
        result = 31 * result + (falemaisPlanDetailed != null ? falemaisPlanDetailed.hashCode() : 0);
        return result;
    }
}
