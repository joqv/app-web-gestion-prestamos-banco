package com.cibertec.mesaverde.presentation.prestamos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CuotasPrestamoResponseDto {

    private Long id;

    private Integer numeroCuota;
    private LocalDate fechaVencimiento;
    private BigDecimal montoTotalCuota;

    private BigDecimal montoPagado;
    private LocalDate fechaPago;

    private String estadoCuota;
}
