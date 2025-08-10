package com.cibertec.mesaverde.presentation.prestamos.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CuotasPrestamoRequestDto {
    private Long prestamoId;

    private Integer numeroCuota;
    private LocalDate fechaVencimiento;

    private BigDecimal montoCapital;
    private BigDecimal montoInteres;
    private BigDecimal montoTotalCuota;

    private BigDecimal montoPagado;
    private LocalDate fechaPago;

    private String estadoCuota;
    private Long movimientoPagoId;
}
