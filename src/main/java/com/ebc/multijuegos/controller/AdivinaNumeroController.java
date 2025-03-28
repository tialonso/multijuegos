package com.ebc.multijuegos.controller;

import com.ebc.multijuegos.dto.AdivinaRespuesta;
import com.ebc.multijuegos.service.AdivinaNumeroService;
import org.springframework.web.bind.annotation.*;

@RestController //Define que esta clase es un controlador REST en Spring Boot, Permite que los métodos devuelvan respuestas JSON o texto plano directamente.
@RequestMapping("/adivina") //Define la ruta base de todas las solicitudes dentro de esta clase.
public class AdivinaNumeroController {
    private final AdivinaNumeroService adivinaNumeroService;

    public AdivinaNumeroController(AdivinaNumeroService adivinaNumeroService) {
        this.adivinaNumeroService = adivinaNumeroService;
    }

    @GetMapping //Define que este método manejará solicitudes GET
    public AdivinaRespuesta adivinarNumero(@RequestParam int intento) { // Extrae valores de la query string (?intento=5) y los pasa al método.
        return adivinaNumeroService.adivinar(intento);
    }
}