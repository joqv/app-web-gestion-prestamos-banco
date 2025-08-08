package com.cibertec.mesaverde.domain.cuentas.repository;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import org.springframework.data.jpa.repository.Query;

public interface CuentaBancariaRepository {

    CuentaBancariaModel obtenerCuentaBancaria(String numero);

    CuentaBancariaModel guardarDeposito(CuentaBancariaModel cuentaBancariaModel);
}
