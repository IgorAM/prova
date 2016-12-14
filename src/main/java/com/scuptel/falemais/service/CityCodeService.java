package com.scuptel.falemais.service;

import com.scuptel.falemais.model.CityCode;

import java.util.List;

public interface CityCodeService {
    List<CityCode> findAll();
    CityCode findById(Long id);
    List<CityCode> destinationsByOrigin(Long originCityCodeId);
}
