package com.cibertec.mesaverde.domain.prestamos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class PrestamoModel {
    
    private Long id;
    private ClienteModel cliente;
    private CuentaBancariaModel cuentaDesembolso;
    private MonedaModel moneda;
    private BigDecimal montoPrincipal;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;
    private BigDecimal montoCuotaMensual;
    private BigDecimal saldoPendiente;
    private String estadoPrestamo;
    private List<CuotasPrestamoModel> cuotas; // Lista de cuotas asociadas al préstamo
}
