package com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper;

import java.util.ArrayList;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.infrastructure.mapper.ClienteMapper;
import com.cibertec.mesaverde.infrastructure.mapper.CuentaBancariaMapper;
import com.cibertec.mesaverde.infrastructure.mapper.MonedaMapper;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.PrestamoEntity;

@Component
public class PrestamoMapper {

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private CuentaBancariaMapper cuentaBancariaMapper;

    @Autowired
    private MonedaMapper monedaMapper;

    @Autowired
    private CuotasPrestamoMapper cuotasPrestamoMapper;

    /**
     * Convierte de Entity (base de datos) a Model (dominio)
     */
    public PrestamoModel toModel(PrestamoEntity entity) {
        if (entity == null) return null;

        return PrestamoModel.builder()
                .id(entity.getId())
                // Convertimos clienteEntity → clienteModel usando su mapper
                .cliente(clienteMapper.toModel(entity.getCliente()))
                // Convertimos cuentaDesembolsoEntity → cuentaBancariaModel
                .cuentaDesembolso(cuentaBancariaMapper.toModel(entity.getCuentaDesembolso()))
                // Convertimos monedaEntity → monedaModel
                .moneda(monedaMapper.toModel(entity.getMoneda()))
                .montoPrincipal(entity.getMontoPrincipal())
                .tasaInteres(entity.getTasaInteres())
                .plazoMeses(entity.getPlazoMeses())
                .fechaInicio(entity.getFechaInicio())
                .fechaFinEstimada(entity.getFechaFinEstimada())
                .montoCuotaMensual(entity.getMontoCuotaMensual())
                .saldoPendiente(entity.getSaldoPendiente())
                .estadoPrestamo(entity.getEstadoPrestamo())
                // Convertimos lista de CuotasPrestamoEntity → lista de CuotasPrestamoModel
                .cuotas(cuotasPrestamoMapper.toModelList(
                        entity.getCuotas() != null ? entity.getCuotas() : new ArrayList<>()
                ))
                .build();
    }

    /**
     * Convierte de Model (dominio) a Entity (base de datos)
     */
    public PrestamoEntity toEntity(PrestamoModel model) {
        if (model == null) return null;

        return PrestamoEntity.builder()
                .id(model.getId())
                .cliente(clienteMapper.toEntity(model.getCliente()))
                .cuentaDesembolso(cuentaBancariaMapper.toEntity(model.getCuentaDesembolso()))
                .moneda(monedaMapper.toEntity(model.getMoneda()))
                .montoPrincipal(model.getMontoPrincipal())
                .tasaInteres(model.getTasaInteres())
                .plazoMeses(model.getPlazoMeses())
                .fechaInicio(model.getFechaInicio())
                .fechaFinEstimada(model.getFechaFinEstimada())
                .montoCuotaMensual(model.getMontoCuotaMensual())
                .saldoPendiente(model.getSaldoPendiente())
                .estadoPrestamo(model.getEstadoPrestamo())
                // Normalmente no guardamos la lista completa de cuotas aquí
                .build();
    }
}
