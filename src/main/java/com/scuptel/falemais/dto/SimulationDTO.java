package com.scuptel.falemais.dto;

import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.Plan;

public class SimulationDTO {

    private Plan plan;
    private CallTax callTax;
    private Integer callTime;

    public SimulationDTO(CallTax callTax, Plan plan, Integer callTime) {
        this.callTax = callTax;
        this.callTime = callTime;
        this.plan = plan;
    }

    public CallTax getCallTax() {
        return callTax;
    }

    public void setCallTax(CallTax callTax) {
        this.callTax = callTax;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
