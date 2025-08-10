package com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.infrastructure.mapper.MovimientoMapper;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;

@Component
public class CuotasPrestamoMapper {

    // Ya no inyectamos PrestamoMapper para evitar el ciclo.
    @Autowired
    private MovimientoMapper movimientoMapper;

    /**
     * Convierte de Entity (base de datos) a Model (dominio)
     */
    public CuotasPrestamoModel toModel(CuotasPrestamoEntity entity) {
        if (entity == null) return null;

        // Aquí evitamos mapear el Prestamo.
        // Si necesitas una referencia al ID del préstamo en el modelo de la cuota,
        // puedes añadir un campo `prestamoId` en el modelo de la cuota y mapearlo aquí.
        return CuotasPrestamoModel.builder()
                .id(entity.getId())
                // .prestamo() <- Ya no se mapea el objeto Prestamo completo
                .numeroCuota(entity.getNumeroCuota())
                .fechaVencimiento(entity.getFechaVencimiento())
                .montoCapital(entity.getMontoCapital())
                .montoInteres(entity.getMontoInteres())
                .montoTotalCuota(entity.getMontoTotalCuota())
                .montoPagado(entity.getMontoPagado())
                .fechaPago(entity.getFechaPago())
                .estadoCuota(entity.getEstadoCuota())
                .movimientoPago(movimientoMapper.toModel(entity.getMovimientoPago()))
                .build();
    }

    /**
     * Convierte de Model (dominio) a Entity (base de datos)
     */
    public CuotasPrestamoEntity toEntity(CuotasPrestamoModel model) {
        if (model == null) return null;

        // Aquí evitamos mapear el Prestamo.
        // Si PrestamoEntity necesita el Prestamo completo, tendrás que manejarlo
        // en otro servicio o componente, no en el mapper.
        return CuotasPrestamoEntity.builder()
                .id(model.getId())
                // .prestamo() <- Ya no se mapea el objeto Prestamo completo
                .numeroCuota(model.getNumeroCuota())
                .fechaVencimiento(model.getFechaVencimiento())
                .montoCapital(model.getMontoCapital())
                .montoInteres(model.getMontoInteres())
                .montoTotalCuota(model.getMontoTotalCuota())
                .montoPagado(model.getMontoPagado())
                .fechaPago(model.getFechaPago())
                .estadoCuota(model.getEstadoCuota())
                .movimientoPago(movimientoMapper.toEntity(model.getMovimientoPago()))
                .build();
    }

    /**
     * Convierte una lista de Entity a una lista de Model
     */
    public List<CuotasPrestamoModel> toModelList(List<CuotasPrestamoEntity> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de Model a una lista de Entity
     */
    public List<CuotasPrestamoEntity> toEntityList(List<CuotasPrestamoModel> models) {
        if (models == null) return new ArrayList<>();
        return models.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}