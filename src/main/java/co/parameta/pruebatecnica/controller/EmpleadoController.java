package co.parameta.pruebatecnica.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoController {

    @GetMapping("/hola")
    public String hello() {
        return "Hola mundo desde Spring Boot";
    }
}
