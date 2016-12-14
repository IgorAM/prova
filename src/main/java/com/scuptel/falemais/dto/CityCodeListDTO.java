package com.scuptel.falemais.dto;

import java.util.ArrayList;
import java.util.List;

public class CityCodeListDTO {

    private List<CityCodeDTO> list;

    public List<CityCodeDTO> getList() {
        return list;
    }

    public void add(CityCodeDTO dto){
        if(getList() == null){
            list = new ArrayList<>();
        }
        getList().add(dto);
    }
}
