package com.cibertec.mesaverde.infrastructure.persistence.cuentas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table (name = "tipo_cuenta")
@Getter
@Setter
@ToString

public class TipoCuentaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;

    @Column(name = "usuario_actualizacion")
    private Integer usuarioActualizacion;
}

