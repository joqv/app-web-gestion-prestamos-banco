package com.cibertec.mesaverde.presentation.clientes.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClienteRequestDto {

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
}
