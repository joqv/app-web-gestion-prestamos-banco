package com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_cuentas")
public class TipoCuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cuenta")
    private Long id;

    @Column(name = "nombre_tipo")
    private String nombreTipo;

    @Column(name = "descripcion")
    private String descripcion;
}
