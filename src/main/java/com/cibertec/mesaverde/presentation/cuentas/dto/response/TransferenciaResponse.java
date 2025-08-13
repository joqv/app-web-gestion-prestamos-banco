package com.cibertec.mesaverde.presentation.cuentas.dto.response;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransferenciaResponse {

    private CuentaBancariaModel numeroCuentaOrigen;
    private CuentaBancariaModel numeroCuentaDestino;
    private String nombreCompleto;
    private BigDecimal monto;
    private String descripcion;
    private Long numeroTransaccion;
    private LocalDateTime fechaHora;
}
