package com.cibertec.mesaverde.domain.prestamos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;

import lombok.Data;

@Data
public class PrestamoModel {
    
    private Long id;
    private ClienteModel idCliente;
    private CuentaBancariaModel idCuentaDesembolso;
    private MonedaModel idMoneda;
    private BigDecimal montoPrincipal;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;
    private BigDecimal montoCuotaMensual;
    private BigDecimal saldoPendiente;
    private String estadoPrestamo;
    

    

}
