package com.cibertec.mesaverde.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.PrestamoEntity;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

   /* @Mapping(source = "cliente.id", target = "idCliente.id")
   @Mapping(source = "cuentaDesembolso.id", target = "idCuentaDesembolso.id")
   @Mapping(source = "moneda.id", target = "idMoneda.id") */
   PrestamoModel toModel(PrestamoEntity prestamoEntity);

  

}
