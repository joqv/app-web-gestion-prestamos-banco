package com.cibertec.mesaverde.application.cuentas.usecase;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.CuentaBancariaRepository;
import com.cibertec.mesaverde.domain.cuentas.service.CuentaBancariaService;
import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.domain.transacciones.repository.TransaccionRepository;
import com.cibertec.mesaverde.presentation.cuentas.dto.request.DepositoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final TransaccionRepository transaccionRepository;

    @Override
    @Transactional
    public CuentaBancariaModel realizarDeposito(DepositoRequestDto requestDto) {

        CuentaBancariaModel cuentaDestino = cuentaBancariaRepository.obtenerCuentaBancaria(requestDto.getNumeroCuentaDestino());

        TransaccionModel transaccion = TransaccionModel.builder()
                .cuentaDestino(cuentaDestino)
                .tipoTransaccion("DEPOSITO")
                .monto(requestDto.getMonto())
                .moneda()
                .build();


        return ;
    }
}
