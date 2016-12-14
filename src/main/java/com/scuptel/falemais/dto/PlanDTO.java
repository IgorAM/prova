package com.scuptel.falemais.dto;

public class PlanDTO {

    private String id;
    private String name;

    public PlanDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlanDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
