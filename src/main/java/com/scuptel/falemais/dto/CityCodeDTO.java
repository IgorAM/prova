package com.scuptel.falemais.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.scuptel.falemais.dto.view.Visibility;

public class CityCodeDTO {

    @JsonView(Visibility.Public.class)
    private String id;
    @JsonView(Visibility.Public.class)
    private String code;

    public CityCodeDTO(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public CityCodeDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
