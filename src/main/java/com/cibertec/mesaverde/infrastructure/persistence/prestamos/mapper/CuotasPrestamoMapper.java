package com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.PrestamoEntity;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.CuotasPrestamoResponseDto;

@Mapper(componentModel = "spring")
public interface CuotasPrestamoMapper {


   // Mapeo con préstamo sin cuotas (evita ciclo infinito)
    @Mapping(target = "prestamo", qualifiedByName = "mapPrestamoSinCuotas")
    CuotasPrestamoModel toModel(CuotasPrestamoEntity cuotasPrestamoEntity);

    // Lista de cuotas (cada una con su préstamo sin cuotas)
    List<CuotasPrestamoModel> toModelList(List<CuotasPrestamoEntity> cuotasPrestamoEntities);

    CuotasPrestamoEntity toEntity(CuotasPrestamoModel cuotasPrestamoModel);

    CuotasPrestamoResponseDto toResponseCuotasPrestamo(CuotasPrestamoModel cuotasPrestamo);

    List<CuotasPrestamoResponseDto> toResponseCuotasPrestamoList(List<CuotasPrestamoModel> cuotasPrestamos);

    // Método auxiliar para mapear préstamo sin cuotas (evitar recursión)
    @Named("mapPrestamoSinCuotas")
    default PrestamoModel mapPrestamoSinCuotas(PrestamoEntity prestamoEntity) {
        if (prestamoEntity == null) {
            return null;
        }

        PrestamoModel model = new PrestamoModel();
        model.setId(prestamoEntity.getId());
        
        // Mapear cliente si existe (puedes usar otro mapper o manual)
        if (prestamoEntity.getCliente() != null) {
            ClienteModel cliente = new ClienteModel();
            cliente.setId(prestamoEntity.getCliente().getId());
            cliente.setNumeroDocumento(prestamoEntity.getCliente().getNumeroDocumento());
            // Mapear otros campos del cliente que necesites
            model.setCliente(cliente);
        }
        
        // Mapear cuenta desembolso si existe
        if (prestamoEntity.getCuentaDesembolso() != null) {
            CuentaBancariaModel cuenta = new CuentaBancariaModel();
            cuenta.setId(prestamoEntity.getCuentaDesembolso().getId());
            // Mapear otros campos de la cuenta que necesites
            model.setCuentaDesembolso(cuenta);
        }
        
        // Mapear moneda si existe
        if (prestamoEntity.getMoneda() != null) {
            MonedaModel moneda = new MonedaModel();
            moneda.setId(prestamoEntity.getMoneda().getId());
            // Mapear otros campos de la moneda que necesites
            model.setMoneda(moneda);
        }
        
        model.setMontoPrincipal(prestamoEntity.getMontoPrincipal());
        model.setTasaInteres(prestamoEntity.getTasaInteres());
        model.setPlazoMeses(prestamoEntity.getPlazoMeses());
        model.setFechaInicio(prestamoEntity.getFechaInicio());
        model.setFechaFinEstimada(prestamoEntity.getFechaFinEstimada());
        model.setMontoCuotaMensual(prestamoEntity.getMontoCuotaMensual());
        model.setSaldoPendiente(prestamoEntity.getSaldoPendiente());
        model.setEstadoPrestamo(prestamoEntity.getEstadoPrestamo());
        
        // NO setear las cuotas aquí - esto evita la recursión
        // model.setCuotas(null); // O simplemente no setear
        
        return model;
    }

    // Método alternativo para mapeo simple sin préstamo (útil en algunos casos)
    @Named("mapCuotaSinPrestamo")
    default CuotasPrestamoModel mapCuotaSinPrestamo(CuotasPrestamoEntity cuotaEntity) {
        if (cuotaEntity == null) {
            return null;
        }

        CuotasPrestamoModel model = new CuotasPrestamoModel();
        model.setId(cuotaEntity.getId());
        model.setNumeroCuota(cuotaEntity.getNumeroCuota());
        model.setFechaPago(cuotaEntity.getFechaPago());
        model.setFechaVencimiento(cuotaEntity.getFechaVencimiento());
        model.setMontoCapital(cuotaEntity.getMontoCapital());
        model.setMontoInteres(cuotaEntity.getMontoInteres());
        model.setMontoPagado(cuotaEntity.getMontoPagado());
        model.setMontoTotalCuota(cuotaEntity.getMontoTotalCuota());
        model.setEstadoCuota(cuotaEntity.getEstadoCuota());
        // NO setear el préstamo
        return model;
    }
    /* CuotasPrestamoModel toModel(CuotasPrestamoEntity cuotasPrestamoEntity);

    CuotasPrestamoEntity toEntity(CuotasPrestamoModel cuotasPrestamoModel);

    CuotasPrestamoResponseDto toResponseCuotasPrestamo(CuotasPrestamoModel cuotasPrestamo); */
}