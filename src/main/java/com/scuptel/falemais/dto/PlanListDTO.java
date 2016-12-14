package com.scuptel.falemais.dto;

import java.util.ArrayList;
import java.util.List;

public class PlanListDTO {

    private List<PlanDTO> list;

    public List<PlanDTO> getList() {
        return list;
    }

    public void add(PlanDTO dto){
        if(getList() == null){
            list = new ArrayList<>();
        }
        getList().add(dto);
    }
}
