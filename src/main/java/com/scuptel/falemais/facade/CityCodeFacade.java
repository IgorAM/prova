package com.scuptel.falemais.facade;

import com.scuptel.falemais.dto.CityCodeListDTO;

public interface CityCodeFacade {
    CityCodeListDTO findAll();
    CityCodeListDTO destinationsByOrigin(String originCityCodeId);
}
