package com.scuptel.falemais.facade;

import com.scuptel.falemais.dto.SimulationRequestDTO;
import com.scuptel.falemais.dto.SimulationResponseDTO;

public interface SimulationFacade {
    SimulationResponseDTO simulate(SimulationRequestDTO dto);
}
