package com.cibertec.mesaverde.presentation.prestamos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.cibertec.mesaverde.presentation.clientes.dto.response.ClienteResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.CuentaBancariaResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.MonedaResponse;

import lombok.Data;

@Data
public class PrestamoResponseDto {
    private Long id;

    private ClienteResponse cliente; // datos básicos del cliente
    private CuentaBancariaResponse cuentaDesembolso;
    private MonedaResponse moneda;

    private BigDecimal montoPrincipal;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;

    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;

    private BigDecimal montoCuotaMensual;
    private BigDecimal saldoPendiente;

    private String estadoPrestamo;

    private List<CuotasPrestamoResponseDto> cuotas;
}
