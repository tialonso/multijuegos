package com.ebc.multijuegos.dto;

public class AdivinaRespuesta {
    private String mensaje;

    public AdivinaRespuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
