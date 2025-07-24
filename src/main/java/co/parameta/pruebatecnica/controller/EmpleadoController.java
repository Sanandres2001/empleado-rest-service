package co.parameta.pruebatecnica.controller;


import co.parameta.pruebatecnica.dto.EmpleadoRequestDTO;
import co.parameta.pruebatecnica.dto.EmpleadoResponseDTO;
import co.parameta.pruebatecnica.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping()
    public ResponseEntity<?> GuardarEmpleado(@ModelAttribute EmpleadoRequestDTO request) {

        ResponseEntity<?> response = empleadoService.procesarEmpleado(request);
        return ResponseEntity.ok(response);
    }
}
