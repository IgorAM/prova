package com.scuptel.falemais.service;

import com.scuptel.falemais.model.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> findAll();
    Plan findById(Long id);
}
