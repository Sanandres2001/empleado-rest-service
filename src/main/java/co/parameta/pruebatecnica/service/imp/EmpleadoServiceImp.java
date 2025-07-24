package co.parameta.pruebatecnica.service.imp;

import co.parameta.pruebatecnica.dto.EmpleadoRequestDTO;
import co.parameta.pruebatecnica.dto.EmpleadoResponseDTO;
import co.parameta.pruebatecnica.service.EmpleadoService;
import co.parameta.pruebatecnica.utils.DateUtils;
import co.parameta.pruebatecnica.ws.client.EmpleadoPort;
import co.parameta.pruebatecnica.ws.client.EmpleadoPortService;
import co.parameta.pruebatecnica.ws.client.GuardarEmpleadoRequest;
import co.parameta.pruebatecnica.ws.client.GuardarEmpleadoResponse;
import jakarta.xml.ws.WebServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmpleadoServiceImp implements EmpleadoService {


    @Override
    public ResponseEntity<?> procesarEmpleado(EmpleadoRequestDTO request) {

        List<String> errores = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimientoVal = null;
        LocalDate fechaVinculacionVal = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String fechaNacStr = sdf.format(request.getFechaNacimiento());
            fechaNacimientoVal = LocalDate.parse(fechaNacStr, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            errores.add("La fecha de nacimiento no tiene un formato válido (yyyy-MM-dd).");
        }

        try {
            String fechaVincStr = sdf.format(request.getFechaVinculacion());
            fechaVinculacionVal = LocalDate.parse(fechaVincStr, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            errores.add("La fecha de vinculación no tiene un formato válido (yyyy-MM-dd).");
        }

        if (request.getNombres() == null || request.getNombres().trim().isEmpty()) {
            errores.add("El nombre es obligatorio");
        }

        if (request.getApellidos() == null || request.getApellidos().trim().isEmpty()) {
            errores.add("El apellido es obligatorio");
        }

        if (request.getTipoDocumento() == null || request.getTipoDocumento().trim().isEmpty()) {
            errores.add("El tipo de documento es obligatorio");
        }

        if (request.getNumeroDocumento() == null || request.getNumeroDocumento().trim().isEmpty()) {
            errores.add("El número de documento es obligatorio");
        }

        if (request.getSalario() == null || request.getSalario() < 0) {
            errores.add("El salario es obligatorio y debe ser mayor a 0");
        }

        if (request.getCargo() == null || request.getCargo().trim().isEmpty()) {
            errores.add("El cargo es obligatorio");
        }

        if (request.getFechaNacimiento() == null) {
            errores.add("La fecha de nacimiento es obligatoria");
        } else {
            Date fechaNacimientoDate = request.getFechaNacimiento();
            LocalDate fechaNacimiento = fechaNacimientoDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            if (edad < 18) {
                errores.add("El empleado debe ser mayor de edad");
            }
        }

        if (!errores.isEmpty()) {
            return ResponseEntity.badRequest().body(errores);
        }

        EmpleadoPortService service = new EmpleadoPortService();
        EmpleadoPort port = service.getEmpleadoPortSoap11();

        DateUtils dateUtils = new DateUtils();

        GuardarEmpleadoRequest soapRequest = new GuardarEmpleadoRequest();
        soapRequest.setNombres(request.getNombres());
        soapRequest.setApellidos(request.getApellidos());
        soapRequest.setTipoDocumento(request.getTipoDocumento());
        soapRequest.setNumeroDocumento(request.getNumeroDocumento());
        soapRequest.setFechaNacimiento(dateUtils.convertirDateAXMLGregorianCalendar(request.getFechaNacimiento()));
        soapRequest.setFechaVinculacion(dateUtils.convertirDateAXMLGregorianCalendar(request.getFechaVinculacion()));
        soapRequest.setCargo(request.getCargo());
        soapRequest.setSalario(request.getSalario());

        try {
            GuardarEmpleadoResponse soapResponse = port.guardarEmpleado(soapRequest);

            LocalDate fechaNacimiento = soapResponse.getFechaNacimiento().toGregorianCalendar()
                    .toZonedDateTime().toLocalDate();

            LocalDate fechaVinculacion = soapResponse.getFechaVinculacion().toGregorianCalendar()
                    .toZonedDateTime().toLocalDate();

            Period edad = Period.between(fechaNacimiento, LocalDate.now());
            Period tiempoVinculacion = Period.between(fechaVinculacion, LocalDate.now());

            EmpleadoResponseDTO respuesta = new EmpleadoResponseDTO();
            respuesta.setEdad(String.format("%d años, %d meses, %d dias",
                    edad.getYears(), edad.getMonths(), edad.getDays()));
            respuesta.setTiempoVinculacion(String.format("%d años, %d meses, %d dias",
                    tiempoVinculacion.getYears(), tiempoVinculacion.getMonths(), tiempoVinculacion.getDays()));

            return ResponseEntity.ok(respuesta);
        } catch (WebServiceException e) {
            throw new RuntimeException("Error al invocar el servicio SOAP: " + e.getMessage(), e);
        }
    }

}
