package com.cibertec.mesaverde.infrastructure.persistence.cuentas.repository;

import com.cibertec.mesaverde.domain.clientes.service.ClienteService;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.CuentaBancariaRepository;
import com.cibertec.mesaverde.domain.cuentas.service.CuentaBancariaService;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa.CuentaBancariaRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CuentaBancariaRepositoryImpl implements CuentaBancariaRepository {

    private final CuentaBancariaRepositoryJpa cuentaBancariaRepositoryJpa;


    @Override
    public CuentaBancariaModel guardarDeposito(CuentaBancariaModel cuentaBancariaModel) {
        return null;
    }

    @Override
    public CuentaBancariaModel obtenerCuentaBancaria(String numero) {
        return null;
    }
}
