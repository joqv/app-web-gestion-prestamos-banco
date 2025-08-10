package com.cibertec.mesaverde.presentation.prestamos.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequestDto {

    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Long idCliente;

    @NotNull(message = "El ID de la cuenta de desembolso no puede ser nulo")
    private Long idCuentaDesembolso;

    @NotNull(message = "El ID de la moneda no puede ser nulo")
    private Long idMoneda;

    @NotNull(message = "El monto principal no puede ser nulo")
    private BigDecimal montoPrincipal;

    @NotNull(message = "La tasa de interés no puede ser nula")
    private BigDecimal tasaInteres;

    @NotNull(message = "El plazo en meses no puede ser nulo")
    private Integer plazoMeses;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha fin estimada no puede ser nula")
    private LocalDate fechaFinEstimada;

    @NotNull(message = "El monto de la cuota mensual no puede ser nulo")
    private BigDecimal montoCuotaMensual;
    
    @NotNull(message = "El saldo pendiente no puede ser nulo")
    private BigDecimal saldoPendiente;

    
    @NotNull(message = "Debe colocar estado Activo")
    private String estadoPrestamo;
}
