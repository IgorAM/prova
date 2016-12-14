package com.scuptel.falemais.facade.impl;

import com.scuptel.falemais.assembler.CityCodeAssembler;
import com.scuptel.falemais.dto.CityCodeListDTO;
import com.scuptel.falemais.facade.CityCodeFacade;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.service.CityCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("cityCodeFacadeImpl")
public class CityCodeFacadeImpl implements CityCodeFacade {

    @Autowired
    @Qualifier("cityCodeServiceImpl")
    private CityCodeService cityCodeService;

    @Autowired
    private CityCodeAssembler cityCodeAssembler;

    public CityCodeListDTO findAll(){
        List<CityCode> cityCodes = cityCodeService.findAll();
        return cityCodeAssembler.toDTOList(cityCodes);
    }

    public CityCodeListDTO destinationsByOrigin(String originCityCodeId){
        if(StringUtils.isBlank(originCityCodeId)){
            return new CityCodeListDTO();
        }

        List<CityCode> cityCodes = cityCodeService.destinationsByOrigin(Long.valueOf(originCityCodeId));
        return cityCodeAssembler.toDTOList(cityCodes);
    }
}
