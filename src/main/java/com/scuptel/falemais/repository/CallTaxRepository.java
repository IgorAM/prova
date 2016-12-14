package com.scuptel.falemais.repository;

import com.scuptel.falemais.model.CallTax;
import com.scuptel.falemais.model.CallTaxId;
import com.scuptel.falemais.model.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface CallTaxRepository extends JpaRepository<CallTax, CallTaxId> {

    CallTax findByOriginCityCodeAndDestinationCityCode(CityCode origin, CityCode destination);

    List<CallTax> findByOriginCityCode_IdOrderByDestinationCityCode_Code(Long originCityCodeId);
}
