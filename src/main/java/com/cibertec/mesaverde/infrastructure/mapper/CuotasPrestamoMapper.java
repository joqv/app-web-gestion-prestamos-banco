package com.cibertec.mesaverde.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;

@Mapper(componentModel = "spring")
public interface CuotasPrestamoMapper {

    CuotasPrestamoModel toModel(CuotasPrestamoEntity cuotasPrestamoEntity);

    CuotasPrestamoEntity toEntity(CuotasPrestamoModel cuotasPrestamoModel);

}
