package com.scuptel.falemais.facade;

import com.scuptel.falemais.assembler.CityCodeAssembler;
import com.scuptel.falemais.config.SpringRootConfig;
import com.scuptel.falemais.dto.CityCodeDTO;
import com.scuptel.falemais.dto.CityCodeListDTO;
import com.scuptel.falemais.facade.impl.CityCodeFacadeImpl;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.service.impl.CityCodeServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class})
public class CityCodeFacadeTest {

    @InjectMocks
    private CityCodeFacadeImpl cityCodeFacade;

    @Mock
    private CityCodeAssembler cityCodeAssembler;

    @Mock
    private CityCodeServiceImpl cityCodeService;

    @Test
    public void no_city_code_found_finding_all(){
        CityCodeListDTO expectedCityCodeListDTO = new CityCodeListDTO();

        when(cityCodeService.findAll()).thenReturn(null);
        when(cityCodeAssembler.toDTOList(null)).thenReturn(expectedCityCodeListDTO);

        CityCodeListDTO cityCodeListDTO = cityCodeFacade.findAll();

        assertTrue(cityCodeListDTO.equals(expectedCityCodeListDTO));
    }

    @Test
    public void city_code_list_found_finding_all(){

        CityCode expectedCityCode = new CityCode(1l, 11);
        List<CityCode> expectedCityCodeList = Collections.singletonList(expectedCityCode);

        when(cityCodeService.findAll()).thenReturn(expectedCityCodeList);


        CityCodeDTO expectedCityCodeDTO = new CityCodeDTO("1", "11");

        CityCodeListDTO expectedCityCodeListDTO = new CityCodeListDTO();
        expectedCityCodeListDTO.add(expectedCityCodeDTO);

        when(cityCodeAssembler.toDTOList(expectedCityCodeList)).thenReturn(expectedCityCodeListDTO);


        CityCodeListDTO cityCodeListDTO = cityCodeFacade.findAll();

        assertNotNull(cityCodeListDTO);
        assertNotNull(cityCodeListDTO.getList());
        assertEquals(cityCodeListDTO.getList().get(0), expectedCityCodeListDTO.getList().get(0));
        assertEquals(cityCodeListDTO, expectedCityCodeListDTO);
    }

    @Test
    public void destinations_by_origin_invalid_argument(){
        CityCodeListDTO cityCodeListDTO = cityCodeFacade.destinationsByOrigin(null);

        assertNotNull(cityCodeListDTO);
        assertTrue(CollectionUtils.isEmpty(cityCodeListDTO.getList()));
    }

    @Test
    public void destinations_by_origin(){
        CityCode origin11 = new CityCode(1l, 11);

        CityCode destination16 = new CityCode(2l, 16);
        CityCode destination17 = new CityCode(3l, 17);
        List<CityCode> expectedCityCodes = Arrays.asList(destination16, destination17);

        when(cityCodeService.destinationsByOrigin(origin11.getId())).thenReturn(expectedCityCodes);

        CityCodeDTO expectedCityCodeDTO16 = new CityCodeDTO("2", "16");
        CityCodeDTO expectedCityCodeDTO17 = new CityCodeDTO("3", "17");

        CityCodeListDTO expectedCityCodeListDTO = new CityCodeListDTO();
        expectedCityCodeListDTO.add(expectedCityCodeDTO16);
        expectedCityCodeListDTO.add(expectedCityCodeDTO17);

        when(cityCodeAssembler.toDTOList(expectedCityCodes)).thenReturn(expectedCityCodeListDTO);

        CityCodeListDTO cityCodeListDTO = cityCodeFacade.destinationsByOrigin(String.valueOf(origin11.getId()));

        assertNotNull(cityCodeListDTO);
        assertTrue(CollectionUtils.isNotEmpty(cityCodeListDTO.getList()));
        assertTrue(cityCodeListDTO.getList().size() == 2);
        assertEquals(cityCodeListDTO.getList().get(0), expectedCityCodeDTO16);
        assertEquals(cityCodeListDTO.getList().get(1), expectedCityCodeDTO17);
    }


}
