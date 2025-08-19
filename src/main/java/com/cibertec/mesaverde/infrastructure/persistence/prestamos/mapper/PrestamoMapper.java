package com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
import com.cibertec.mesaverde.presentation.prestamos.dto.request.PrestamoRequestDto;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.PrestamoResponseDto;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {
    @Mapping(target = "cuotas", qualifiedByName = "mapCuotasSinPrestamo")
    PrestamoModel toModel(PrestamoEntity prestamoEntity);

    PrestamoEntity toEntity(PrestamoModel prestamoModel);

    PrestamoResponseDto toResponsePrestamo(PrestamoModel prestamoModel);

    @Mapping(target = "cliente", qualifiedByName = "mapClienteFromId")
    @Mapping(target = "cuentaDesembolso", qualifiedByName = "mapCuentaDesembolsoFromId")
    @Mapping(target = "moneda", qualifiedByName = "mapMonedaFromId")
    PrestamoModel toModel(PrestamoRequestDto prestamoRequestDto);

    @Named("mapCuotasSinPrestamo")
    default List<CuotasPrestamoModel> mapCuotasSinPrestamo(List<CuotasPrestamoEntity> cuotas) {
        if (cuotas == null)
            return null;

        return cuotas.stream()
                .map(cuota -> {
                    CuotasPrestamoModel model = new CuotasPrestamoModel();
                    // mapear campos de cuota excepto el campo que apunta al PrestamoModel para
                    // evitar recursión
                    model.setId(cuota.getId());
                    model.setNumeroCuota(cuota.getNumeroCuota());
                    model.setFechaPago(cuota.getFechaPago());
                    model.setFechaVencimiento(cuota.getFechaVencimiento());
                    model.setMontoCapital(cuota.getMontoCapital());
                    model.setMontoInteres(cuota.getMontoInteres());
                    model.setMontoPagado(cuota.getMontoPagado());
                    model.setMontoTotalCuota(cuota.getMontoTotalCuota());
                    model.setEstadoCuota(cuota.getEstadoCuota());
                    // NO setear model.setPrestamo(...) para evitar ciclo
                    return model;
                })
                .collect(Collectors.toList());
    }

    @Named("mapClienteFromId")
    default ClienteModel mapClienteFromId(Long id) {
        if (id == null) return null;
        ClienteModel cliente = new ClienteModel();
        cliente.setId(id);
        return cliente;
    }

    @Named("mapCuentaDesembolsoFromId")
    default CuentaBancariaModel mapCuentaDesembolsoFromId(Long id) {
        if (id == null) return null;
        CuentaBancariaModel cuentaDesembolso = new CuentaBancariaModel();
        cuentaDesembolso.setId(id);
        return cuentaDesembolso;
    }

    @Named("mapMonedaFromId")
    default MonedaModel mapMonedaFromId(Long id) {
        if (id == null) return null;
        MonedaModel moneda = new MonedaModel();
        moneda.setId(id);
        return moneda;
    }
}
