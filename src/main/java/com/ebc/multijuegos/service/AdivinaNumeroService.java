package com.ebc.multijuegos.service;

import com.ebc.multijuegos.dto.AdivinaRespuesta;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service //Marca la clase como un componente de servicio en Spring Boot.
// Indica que contiene lógica de negocio y no es un controlador ni un repositorio.
// Spring Boot lo detecta automáticamente y lo gestiona como un Bean.
public class AdivinaNumeroService {
    private int numeroSecreto;
    private final Random random = new Random();

    public AdivinaNumeroService() {
        generarNuevoNumero();
    }

    private void generarNuevoNumero() {
        numeroSecreto = random.nextInt(10) + 1;
    }

    public AdivinaRespuesta adivinar(int intento) {
        if (intento < numeroSecreto) {
            return new AdivinaRespuesta("Demasiado bajo!");
        } else if (intento > numeroSecreto) {
            return new AdivinaRespuesta("Demasiado alto!");
        } else {
            generarNuevoNumero();
            return new AdivinaRespuesta("¡Correcto! Se ha generado un nuevo número.");
        }
    }
}
