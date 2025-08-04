package com.cibertec.mesaverde.domain.clientes.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteModel {

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
}
