package com.cibertec.mesaverde.presentation.prestamos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.cibertec.mesaverde.infrastructure.persistence.clientes.entity.ClienteEntity;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.CuentaBancariaEntity;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.MonedaEntity;
import com.cibertec.mesaverde.presentation.clientes.dto.response.ClienteResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.CuentaBancariaResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.MonedaResponse;

import lombok.Data;

@Data
public class PrestamoResponseDto {
    private Long id;

    private CuentaBancariaEntity cuentaDesembolso;

    private BigDecimal montoPrincipal;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;

    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;

    private BigDecimal montoCuotaMensual;
    private BigDecimal saldoPendiente;

    private List<CuotasPrestamoResponseDto> cuotas;
}
