package com.cibertec.mesaverde.domain.cuentas.service;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.presentation.cuentas.dto.request.DepositoRequestDto;

public interface CuentaBancariaService {

    CuentaBancariaModel realizarDeposito(DepositoRequestDto depositoRequestDto);
}
