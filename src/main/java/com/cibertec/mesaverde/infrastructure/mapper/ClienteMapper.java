package com.cibertec.mesaverde.infrastructure.mapper;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.infrastructure.persistence.clientes.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteModel toModel(ClienteEntity clienteEntity);
}
