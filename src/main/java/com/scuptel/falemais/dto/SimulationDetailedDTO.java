package com.scuptel.falemais.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.scuptel.falemais.dto.view.Visibility;

public class SimulationDetailedDTO {

    @JsonView(Visibility.Public.class)
    private float price;

    @JsonView(Visibility.Public.class)
    private String plan;

    public SimulationDetailedDTO(float price) {
        this.price = price;
    }

    public SimulationDetailedDTO(String plan, float price) {
        this.plan = plan;
        this.price = price;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SimulationDetailedDTO{" +
                "plan='" + plan + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimulationDetailedDTO that = (SimulationDetailedDTO) o;

        if (Float.compare(that.price, price) != 0) return false;
        return !(plan != null ? !plan.equals(that.plan) : that.plan != null);

    }

    @Override
    public int hashCode() {
        int result = (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        return result;
    }
}
