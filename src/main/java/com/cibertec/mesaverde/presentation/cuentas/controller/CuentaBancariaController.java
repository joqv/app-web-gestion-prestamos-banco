package com.cibertec.mesaverde.presentation.cuentas.controller;

import com.cibertec.mesaverde.domain.cuentas.service.CuentaBancariaService;
import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.presentation.cuentas.dto.request.DepositoRequestDto;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.DepositoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/cuentas")
@RequiredArgsConstructor
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;

    @PostMapping(value = "/deposito")
    public DepositoResponse realizarDeposito(@RequestBody DepositoRequestDto requestDto) {

        TransaccionModel transaccion = cuentaBancariaService.procesarDeposito(requestDto);

        String mensaje = "Deposito realizado con extio. Número de transacción:" + transaccion.getId();

        return new DepositoResponse(mensaje);
    }
}
