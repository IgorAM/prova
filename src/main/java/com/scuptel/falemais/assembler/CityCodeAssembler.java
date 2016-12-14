package com.scuptel.falemais.assembler;

import com.scuptel.falemais.dto.CityCodeDTO;
import com.scuptel.falemais.dto.CityCodeListDTO;
import com.scuptel.falemais.model.CityCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityCodeAssembler {

    public CityCodeDTO toDTO(CityCode cityCode){
        return new CityCodeDTO(String.valueOf(cityCode.getId()), String.valueOf(cityCode.getCode()));
    }

    public CityCodeListDTO toDTOList(List<CityCode> cityCodes){
        if(CollectionUtils.isEmpty(cityCodes)){
            return new CityCodeListDTO();
        }

        CityCodeListDTO dtos = new CityCodeListDTO();

        for(CityCode cityCode : cityCodes){
            dtos.add(toDTO(cityCode));
        }

        return dtos;
    }
}
