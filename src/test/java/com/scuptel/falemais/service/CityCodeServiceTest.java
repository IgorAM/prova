package com.scuptel.falemais.service;

import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.repository.CallTaxRepository;
import com.scuptel.falemais.repository.CityCodeRepository;
import com.scuptel.falemais.service.impl.CityCodeServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class CityCodeServiceTest {

    @Mock
    private CityCodeRepository cityCodeRepository;

    @Mock
    private CallTaxRepository callTaxRepository;

    @InjectMocks
    private CityCodeServiceImpl cityCodeService;

    @Test
    public void invalid_argument_id(){
        CityCode cityCode = cityCodeService.findById(null);
        assertNull(cityCode);
    }

    @Test
    public void success_find_one(){
        CityCode cityCodeExpected = new CityCode(1l, 16);
        when(cityCodeRepository.findOne(1l)).thenReturn(cityCodeExpected);

        CityCode cityCode = cityCodeService.findById(1l);
        assertTrue(cityCode.equals(cityCodeExpected));
    }

    @Test
    public void destinations_by_origin(){
        CityCode origin11 = new CityCode(1l, 11);
        CityCode destination16 = new CityCode(2l, 16);
        CityCode destination17 = new CityCode(3l, 17);

        CallTax callTax11To16 = new CallTax(destination16, origin11, new BigDecimal(1.9));
        CallTax callTax11To17 = new CallTax(destination17, origin11, new BigDecimal(1.7));
        List<CallTax> expectedList = Arrays.asList(callTax11To16, callTax11To17);

        when(callTaxRepository.findByOriginCityCode_IdOrderByDestinationCityCode_Code(origin11.getId())).thenReturn(expectedList);

        List<CityCode> cityCodes = cityCodeService.destinationsByOrigin(origin11.getId());

        assertNotNull(cityCodes);
        assertTrue(CollectionUtils.isNotEmpty(cityCodes));
        assertTrue(cityCodes.size() == 2);
        assertEquals(cityCodes.get(0), (destination16));
        assertEquals(cityCodes.get(1), (destination17));
    }

}
