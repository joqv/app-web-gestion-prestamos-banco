package com.cibertec.mesaverde.domain.cuentas.service;

import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.presentation.cuentas.dto.request.DepositoRequestDto;

public interface CuentaBancariaService {

    TransaccionModel procesarDeposito(DepositoRequestDto depositoRequestDto);
}
