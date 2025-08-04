package com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "monedas")
public class MonedaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moneda")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "simbolo")
    private String simbolo;
}
