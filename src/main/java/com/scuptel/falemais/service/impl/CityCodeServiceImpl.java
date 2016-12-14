package com.scuptel.falemais.service.impl;

import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CityCode;
import com.scuptel.falemais.repository.CallTaxRepository;
import com.scuptel.falemais.repository.CityCodeRepository;
import com.scuptel.falemais.service.CityCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("cityCodeServiceImpl")
public class CityCodeServiceImpl implements CityCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityCodeServiceImpl.class);

    @Autowired
    private CityCodeRepository cityCodeRepository;

    @Autowired
    private CallTaxRepository callTaxRepository;

    public List<CityCode> findAll(){
        return cityCodeRepository.findAll(sortByCode());
    }

    private Sort sortByCode() {
        return new Sort(Sort.Direction.ASC, "code");
    }

    public CityCode findById(Long id) {
        try {
            return cityCodeRepository.findOne(id);
        }catch(IllegalArgumentException illegalException){
            LOGGER.error("Argument CityCode id is null.");
            return null;
        }
    }

    public List<CityCode> destinationsByOrigin(Long originCityCodeId){
        List<CallTax> callTaxsByOriginCityCode = callTaxRepository.findByOriginCityCode_IdOrderByDestinationCityCode_Code(originCityCodeId);
        return callTaxsByOriginCityCode.stream().map(CallTax::getDestinationCityCode).collect(Collectors.toList());
    }
}
