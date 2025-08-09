package com.cibertec.mesaverde.infrastructure.persistence.cuentas.repository;

import com.cibertec.mesaverde.domain.clientes.service.ClienteService;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.CuentaBancariaRepository;
import com.cibertec.mesaverde.domain.cuentas.service.CuentaBancariaService;
import com.cibertec.mesaverde.infrastructure.mapper.CuentaBancariaMapper;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.CuentaBancariaEntity;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa.CuentaBancariaRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CuentaBancariaRepositoryImpl implements CuentaBancariaRepository {

    private final CuentaBancariaRepositoryJpa cuentaBancariaRepositoryJpa;
    private final CuentaBancariaMapper cuentaBancariaMapper;


    @Override
    public CuentaBancariaModel guardarDeposito(CuentaBancariaModel cuentaBancariaModel) {

        CuentaBancariaEntity entity = cuentaBancariaMapper.toEntity(cuentaBancariaModel);
        CuentaBancariaEntity cuentaGuardada = cuentaBancariaRepositoryJpa.save(entity);

        return cuentaBancariaMapper.toModel(cuentaGuardada);
    }

    @Override
    public CuentaBancariaModel obtenerCuentaBancaria(String numero) {

        CuentaBancariaEntity entity = cuentaBancariaRepositoryJpa.getCuentaBancariaInterna(numero);
        CuentaBancariaModel model = cuentaBancariaMapper.toModel(entity);

        return model;
    }
}
