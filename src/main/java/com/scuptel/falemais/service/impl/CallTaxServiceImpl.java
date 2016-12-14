package com.scuptel.falemais.service.impl;

import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.repository.CallTaxRepository;
import com.scuptel.falemais.service.CallTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("callTaxServiceImpl")
public class CallTaxServiceImpl implements CallTaxService {

    @Autowired
    private CallTaxRepository callTaxRepository;

    public CallTax findCallTax(CityCode origin, CityCode destination){
        return callTaxRepository.findByOriginCityCodeAndDestinationCityCode(origin, destination);
    }

}
