package com.cibertec.mesaverde.presentation.prestamos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.service.CuotasPrestamoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/cuotas")
@RequiredArgsConstructor
public class CuotasPrestamoController {

    private final CuotasPrestamoService cuotasPrestamoService;

    @GetMapping
    public List<CuotasPrestamoModel> listarCuotas() {
        return cuotasPrestamoService.listarCuotasPrestamos();
    }
}
