package com.scuptel.falemais.service;


import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.repository.CallTaxRepository;
import com.scuptel.falemais.service.impl.CallTaxServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class CallTaxServiceTest {

    @InjectMocks
    private CallTaxServiceImpl callTaxService;

    @Mock
    private CallTaxRepository callTaxRepository;

    @Test
    public void invalid_argument_city_code(){

        CityCode cityCode = new CityCode(0l, 21);

        when(callTaxRepository.findByOriginCityCodeAndDestinationCityCode(cityCode, cityCode)).thenReturn(null);

        CallTax callTax = callTaxService.findCallTax(cityCode, cityCode);

        assertNull(callTax);

        verify(callTaxRepository, only()).findByOriginCityCodeAndDestinationCityCode(cityCode, cityCode);
    }

    @Test
    public void success_find_one(){
        CityCode originCityCode = new CityCode(1l, 11);
        CityCode destinationCityCode = new CityCode(2l, 16);
        CallTax expectedCallTax = new CallTax(destinationCityCode, originCityCode, new BigDecimal(1.90));

        when(callTaxRepository.findByOriginCityCodeAndDestinationCityCode(originCityCode, destinationCityCode)).thenReturn(expectedCallTax);

        CallTax callTax = callTaxService.findCallTax(originCityCode, destinationCityCode);

        assertTrue(callTax.equals(expectedCallTax));
    }


}