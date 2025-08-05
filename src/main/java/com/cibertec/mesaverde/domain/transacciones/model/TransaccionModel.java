package com.cibertec.mesaverde.domain.transacciones.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransaccionModel {

    private BigDecimal monto;
    private String descripcion;
}
