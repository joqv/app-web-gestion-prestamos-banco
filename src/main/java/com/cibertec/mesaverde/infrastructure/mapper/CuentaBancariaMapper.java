package com.cibertec.mesaverde.infrastructure.mapper;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.CuentaBancariaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuentaBancariaMapper {

    CuentaBancariaModel toModel(CuentaBancariaEntity cuentaBancariaEntity);

    CuentaBancariaEntity toEntity(CuentaBancariaModel cuentaBancariaModel);
}
