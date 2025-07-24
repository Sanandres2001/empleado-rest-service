package co.parameta.pruebatecnica.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmpleadoResponseDTO {
    private String tiempoVinculacion;
    private String edad;

    public String getTiempoVinculacion() {
        return tiempoVinculacion;
    }

    public void setTiempoVinculacion(String tiempoVinculacion) {
        this.tiempoVinculacion = tiempoVinculacion;
    }

    public String getEdad() {
        return edad;
    }

    public EmpleadoResponseDTO() {
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public EmpleadoResponseDTO(String tiempoVinculacion, String edad) {
        this.tiempoVinculacion = tiempoVinculacion;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "EmpleadoResponse{" +
                "tiempoVinculacion=" + tiempoVinculacion +
                ", edad=" + edad +
                '}';
    }
}
