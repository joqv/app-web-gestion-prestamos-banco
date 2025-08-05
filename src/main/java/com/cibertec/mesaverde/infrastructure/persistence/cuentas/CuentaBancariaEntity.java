package com.cibertec.mesaverde.infrastructure.persistence.cuentas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas_bancarias")
@Getter
@Setter
@ToString

public class CuentaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta", nullable = false)
    private TipoCuentaEntity tipoCuenta;

    @Column(name = "numero_cuenta", nullable = false, length = 50)
    private String numeroCuenta;

    @Column(name = "numero_cci_interbancario", length = 50)
    private String numeroCciInterbancario;

    @Column(name = "saldo", nullable = false, precision = 18, scale = 4)
    private BigDecimal saldo;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "estado_cuenta", length = 20)
    private String estadoCuenta;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;

    @Column(name = "usuario_actualizacion")
    private Integer usuarioActualizacion;
}


