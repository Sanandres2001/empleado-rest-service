package co.parameta.pruebatecnica.service;

import co.parameta.pruebatecnica.dto.EmpleadoRequestDTO;
import co.parameta.pruebatecnica.dto.EmpleadoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface EmpleadoService {
    ResponseEntity<?> procesarEmpleado(EmpleadoRequestDTO request);

}
