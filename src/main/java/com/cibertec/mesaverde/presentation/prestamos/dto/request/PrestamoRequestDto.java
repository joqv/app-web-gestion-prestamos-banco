package com.cibertec.mesaverde.presentation.prestamos.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequestDto {

    
    private Long cliente;

    private String cuentaDesembolso;

    private Long moneda;

    private BigDecimal montoPrincipal;

    private BigDecimal tasaInteres;

    private Integer plazoMeses;

    private LocalDate fechaInicio;

    private LocalDate fechaFinEstimada;

    private String estadoPrestamo;



}
