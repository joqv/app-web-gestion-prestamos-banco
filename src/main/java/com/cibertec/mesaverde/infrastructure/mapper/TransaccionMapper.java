package com.cibertec.mesaverde.infrastructure.mapper;

import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.entity.TransaccionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    TransaccionModel toModel(TransaccionEntity transaccionEntity);
}
