package com.cibertec.mesaverde.presentation.prestamos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.service.PrestamoService;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper.PrestamoMapper;
import com.cibertec.mesaverde.presentation.prestamos.dto.request.PrestamoRequestDto;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.PrestamoResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/prestamos")
@RequiredArgsConstructor
@Tag(name = "Prestamos", description = "Operations related to prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;
    private final PrestamoMapper prestamoMapper;

    @GetMapping
    public ResponseEntity<List<PrestamoResponseDto>> listarPrestamos() {
        try {
            List<PrestamoModel> prestamos = prestamoService.listarPrestamos();
            List<PrestamoResponseDto> response = prestamos.stream()
                    .map(prestamoMapper::toResponsePrestamo)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestamoResponseDto> registrarPrestamo(@RequestBody PrestamoRequestDto dto) {
        try {
            if (dto == null) {
                return ResponseEntity.badRequest().build();
            }

            // Llamamos directamente al servicio
            PrestamoModel prestamoCreado = prestamoService.registrarPrestamo(dto);

            // Mapeamos la respuesta
            PrestamoResponseDto response = prestamoMapper.toResponsePrestamo(prestamoCreado);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}