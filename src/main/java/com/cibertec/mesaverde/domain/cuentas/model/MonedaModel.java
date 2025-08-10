package com.cibertec.mesaverde.domain.cuentas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonedaModel {

    private Long id;
    private String codigo;
    private String nombre;
    private String simbolo;
}
