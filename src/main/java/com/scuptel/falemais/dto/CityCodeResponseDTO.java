package com.scuptel.falemais.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.scuptel.falemais.dto.view.Visibility;

import java.util.List;

public class CityCodeResponseDTO {

    @JsonView(Visibility.Public.class)
    private Integer code;

    @JsonView(Visibility.Public.class)
    private List<CityCodeDTO> destinations;

    public CityCodeResponseDTO(Integer code) {
        this.code = code;
    }

    public CityCodeResponseDTO(Integer code, List<CityCodeDTO> destinations) {
        this.code = code;
        this.destinations = destinations;
    }

    public CityCodeResponseDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<CityCodeDTO> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<CityCodeDTO> destinations) {
        this.destinations = destinations;
    }
}
