package com.cibertec.mesaverde.presentation.prestamos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.service.CuotasPrestamoService;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper.CuotasPrestamoMapper;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.CuotasPrestamoResponseDto;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api/cuotas")
@RequiredArgsConstructor
public class CuotasPrestamoController {

    private final CuotasPrestamoService cuotasPrestamoService;
    private final CuotasPrestamoMapper cuotasMapper;

    @GetMapping
    public List<CuotasPrestamoModel> listarCuotas() {
        return cuotasPrestamoService.listarCuotasPrestamos();
    }

    @GetMapping("/cliente/dni/{dni}")
    public ResponseEntity<List<CuotasPrestamoResponseDto>> listarCuotasDni(@PathVariable String dni) {

        List<CuotasPrestamoModel> cuotas = cuotasPrestamoService.buscarPorClienteDni(dni);
        List<CuotasPrestamoResponseDto> response = cuotas
            .stream()
            .map(cuotasMapper::toResponseCuotasPrestamo)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(response);
    }
    
}
