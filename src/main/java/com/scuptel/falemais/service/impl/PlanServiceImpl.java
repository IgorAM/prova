package com.scuptel.falemais.service.impl;

import com.scuptel.falemais.model.Plan;
import com.scuptel.falemais.repository.PlanRepository;
import com.scuptel.falemais.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("planServiceImpl")
public class PlanServiceImpl implements PlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> findAll(){
        return planRepository.findAll(sortByName());
    }

    private Sort sortByName() {
        return new Sort(Sort.Direction.ASC, "name");
    }

    public Plan findById(Long id){
        try {
            return planRepository.findOne(id);
        }catch(IllegalArgumentException illegalException){
            LOGGER.error("Argument Plan id is null.");
            return null;
        }
    }

}
