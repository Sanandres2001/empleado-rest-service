package co.parameta.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmpleadoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotNull(message = "La fecha de vinculación es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVinculacion;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser positivo")
    private Double salario;

    public EmpleadoRequestDTO(String nombres, String apellidos, String tipoDocumento, String numeroDocumento, Date fechaNacimiento, Date fechaVinculacion, String cargo, Double salario) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaVinculacion = fechaVinculacion;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "EmpleadoRequest{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaVinculacion=" + fechaVinculacion +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                '}';
    }
}