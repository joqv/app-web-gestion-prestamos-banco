package com.cibertec.mesaverde.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.MonedaEntity;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.entity.TransaccionEntity;

@Mapper(componentModel = "spring")
public interface MonedaMapper {

    MonedaModel toModel(MonedaEntity monedaEntity);

    MonedaEntity toEntity(MonedaModel monedaModel);
}
