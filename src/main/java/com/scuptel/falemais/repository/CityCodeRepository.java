package com.scuptel.falemais.repository;

import com.scuptel.falemais.model.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCodeRepository extends JpaRepository<CityCode, Long> {

}
