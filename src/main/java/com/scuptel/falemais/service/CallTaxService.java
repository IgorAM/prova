package com.scuptel.falemais.service;

import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;

public interface CallTaxService {

    CallTax findCallTax(CityCode origin, CityCode destination);
}
