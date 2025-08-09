package com.cibertec.mesaverde.application.cuentas.usecase;

import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.CuentaBancariaRepository;
import com.cibertec.mesaverde.domain.cuentas.service.CuentaBancariaService;
import com.cibertec.mesaverde.domain.transacciones.model.MovimientoModel;
import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.domain.transacciones.repository.MovimientoRepository;
import com.cibertec.mesaverde.domain.transacciones.repository.TransaccionRepository;
import com.cibertec.mesaverde.presentation.cuentas.dto.request.DepositoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final TransaccionRepository transaccionRepository;
    private final MovimientoRepository movimientoRepository;

    @Override
    @Transactional
    public TransaccionModel procesarDeposito(DepositoRequestDto requestDto) {

        CuentaBancariaModel cuentaDestino = cuentaBancariaRepository.obtenerCuentaBancaria(requestDto.getNumeroCuentaDestino());

        TransaccionModel transaccion = TransaccionModel.builder()
                .cuentaDestino(cuentaDestino)
                .tipoTransaccion("DEPOSITO")
                .monto(requestDto.getMonto())
                .moneda(cuentaDestino.getMoneda())
                .fechaHoraTransaccion(LocalDateTime.now())
                .estadoTransaccion("COMPLETADA")
                .descripcion(requestDto.getDescripcion() != null ? requestDto.getDescripcion() : "Depósito en efectivo.")
                .build();

        transaccion = transaccionRepository.guardarTransaccion(transaccion);

        BigDecimal saldoAntes = cuentaDestino.getSaldo();
        cuentaDestino.setSaldo(saldoAntes.add(requestDto.getMonto()).setScale(4, RoundingMode.HALF_UP));
        cuentaBancariaRepository.guardarDeposito(cuentaDestino);

        MovimientoModel movimiento = MovimientoModel.builder()
                .transaccion(transaccion)
                .cuenta(cuentaDestino)
                .tipoMovimiento("DEBITO")
                .monto(requestDto.getMonto())
                .saldoAntes(saldoAntes)
                .saldoDespues(cuentaDestino.getSaldo())
                .fechaHoraMovimiento(LocalDateTime.now())
                .descripcion(transaccion.getDescripcion())
                .build();

        movimientoRepository.guardarMovimiento(movimiento);

        return transaccion;
    }
}
