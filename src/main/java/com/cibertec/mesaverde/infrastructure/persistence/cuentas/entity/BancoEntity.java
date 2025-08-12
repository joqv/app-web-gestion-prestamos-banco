package com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bancos")
public class BancoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private Long id;

    @Column(name = "nombre_banco")
    private String nombreBanco;

    @Column(name = "codigo_banco")
    private String codigoBanco;

    @Column(name = "pais")
    private String pais;

    @Column(name = "ciudad")
    private String ciudad;
}
