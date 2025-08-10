package com.cibertec.mesaverde.presentation.prestamos.controller;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.service.PrestamoService;
import com.cibertec.mesaverde.presentation.clientes.dto.response.ClienteResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.CuentaBancariaResponse;
import com.cibertec.mesaverde.presentation.cuentas.dto.response.MonedaResponse;
import com.cibertec.mesaverde.presentation.prestamos.dto.request.PrestamoRequestDto;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.CuotasPrestamoResponseDto;
import com.cibertec.mesaverde.presentation.prestamos.dto.response.PrestamoResponseDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/prestamos")
@RequiredArgsConstructor
@Tag(name = "Prestamos", description = "Operations related to prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<PrestamoResponseDto>> listarPrestamos() {
        try {
            List<PrestamoModel> prestamos = prestamoService.listarPrestamos();
            List<PrestamoResponseDto> response = prestamos.stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestamoResponseDto> registrarPrestamo(
            HttpServletRequest request,
            @RequestBody(required = false) PrestamoRequestDto dto) {

        try {
            // Debug del request
            System.out.println("Content-Type: " + request.getContentType());
            System.out.println("Content-Length: " + request.getContentLength());
            System.out.println("Method: " + request.getMethod());
            System.out.println("DTO recibido: " + dto);

            // Leer el body manualmente
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("Body manual: " + sb.toString());

            if (dto == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // Tu código original...
            try {
                // Convertir DTO a Model manualmente
                PrestamoModel prestamo = toModel(dto);

                PrestamoModel prestamoCreado = prestamoService.registrarPrestamo(prestamo);
                PrestamoResponseDto response = toResponseDto(prestamoCreado);

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * @PostMapping
     * public ResponseEntity<PrestamoResponseDto>
     * registrarPrestamo(@Valid @RequestBody PrestamoRequestDto dto) {
     * try {
     * // Convertir DTO a Model manualmente
     * PrestamoModel prestamo = toModel(dto);
     * 
     * PrestamoModel prestamoCreado = prestamoService.registrarPrestamo(prestamo);
     * PrestamoResponseDto response = toResponseDto(prestamoCreado);
     * 
     * return ResponseEntity.status(HttpStatus.CREATED).body(response);
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     * }
     * }
     */

    // Metodo para pasar PrestamoRequestdto a Model
    private PrestamoModel toModel(PrestamoRequestDto dto) {
        PrestamoModel modelo = new PrestamoModel();

        // Mapear campos básicos directamente
        modelo.setMontoPrincipal(dto.getMontoPrincipal());
        modelo.setTasaInteres(dto.getTasaInteres());
        modelo.setPlazoMeses(dto.getPlazoMeses());
        modelo.setFechaInicio(dto.getFechaInicio());
        modelo.setFechaFinEstimada(dto.getFechaFinEstimada());
        modelo.setMontoCuotaMensual(dto.getMontoCuotaMensual());
        modelo.setSaldoPendiente(dto.getSaldoPendiente());
        modelo.setEstadoPrestamo(dto.getEstadoPrestamo());

        // Para objetos relacionados, crear objetos con solo el ID
        // El service se encargará de validar que existan y cargar los datos completos
        if (dto.getIdCliente() != null) {
            ClienteModel cliente = new ClienteModel();
            cliente.setId(dto.getIdCliente());
            modelo.setCliente(cliente);
        }

        if (dto.getIdCuentaDesembolso() != null) {
            CuentaBancariaModel cuenta = new CuentaBancariaModel();
            cuenta.setId(dto.getIdCuentaDesembolso());
            modelo.setCuentaDesembolso(cuenta);
        }

        if (dto.getIdMoneda() != null) {
            MonedaModel moneda = new MonedaModel();
            moneda.setId(dto.getIdMoneda());
            modelo.setMoneda(moneda);
        }

        return modelo;
    }
    // Metodo para pasar PrestamoResponseDto a PrestamoModel

    private PrestamoResponseDto toResponseDto(PrestamoModel modelo) {
        PrestamoResponseDto dto = new PrestamoResponseDto();

        dto.setId(modelo.getId());
        dto.setMontoPrincipal(modelo.getMontoPrincipal());
        dto.setTasaInteres(modelo.getTasaInteres());
        dto.setPlazoMeses(modelo.getPlazoMeses());
        dto.setFechaInicio(modelo.getFechaInicio());
        dto.setFechaFinEstimada(modelo.getFechaFinEstimada());
        dto.setMontoCuotaMensual(modelo.getMontoCuotaMensual());
        dto.setSaldoPendiente(modelo.getSaldoPendiente());
        dto.setEstadoPrestamo(modelo.getEstadoPrestamo());

        /*
         * // Campos básicos
         * dto.setId(modelo.getId());
         * dto.setMontoPrincipal(modelo.getMontoPrincipal());
         * dto.setTasaInteres(modelo.getTasaInteres());
         * dto.setPlazoMeses(modelo.getPlazoMeses());
         * dto.setFechaInicio(modelo.getFechaInicio());
         * dto.setFechaFinEstimada(modelo.getFechaFinEstimada());
         * dto.setMontoCuotaMensual(modelo.getMontoCuotaMensual());
         * dto.setSaldoPendiente(modelo.getSaldoPendiente());
         * dto.setEstadoPrestamo(modelo.getEstadoPrestamo());
         * 
         * // Mapear objetos relacionados
         * if (modelo.getCliente() != null) {
         * dto.setCliente(toClienteResponse(modelo.getCliente()));
         * }
         * 
         * if (modelo.getCuentaDesembolso() != null) {
         * dto.setCuentaDesembolso(toCuentaBancariaResponse(modelo.getCuentaDesembolso()
         * ));
         * }
         * 
         * if (modelo.getMoneda() != null) {
         * dto.setMoneda(toMonedaResponse(modelo.getMoneda()));
         * }
         * 
         * // Mapear cuotas si existen
         * if (modelo.getCuotas() != null && !modelo.getCuotas().isEmpty()) {
         * dto.setCuotas(modelo.getCuotas().stream()
         * .map(this::toCuotaResponseDto)
         * .collect(Collectors.toList()));
         * }
         */

        return dto;
    }
    /*
     * // Métodos auxiliares para mapear objetos relacionados
     * private ClienteResponse toClienteResponse(ClienteModel cliente) {
     * ClienteResponse response = new ClienteResponse();
     * response.setId(cliente.getId());
     * response.setNombre(cliente.getNombre());
     * response.setApellido(cliente.getApellido());
     * response.setDni(cliente.getDni());
     * // ... otros campos básicos del cliente
     * return response;
     * }
     * 
     * private CuentaBancariaResponse toCuentaBancariaResponse(CuentaBancariaModel
     * cuenta) {
     * CuentaBancariaResponse response = new CuentaBancariaResponse();
     * response.setId(cuenta.getId());
     * response.setNumeroCuenta(cuenta.getNumeroCuenta());
     * // ... otros campos
     * return response;
     * }
     * 
     * private MonedaResponse toMonedaResponse(MonedaModel moneda) {
     * MonedaResponse response = new MonedaResponse();
     * response.setId(moneda.getId());
     * response.setNombre(moneda.getNombre());
     * return response;
     * }
     * 
     * private CuotasPrestamoResponseDto toCuotaResponseDto(CuotasPrestamoModel
     * cuota) {
     * CuotasPrestamoResponseDto response = new CuotasPrestamoResponseDto();
     * response.setId(cuota.getId());
     * response.setNumeroCuota(cuota.getNumeroCuota());
     * response.setFechaVencimiento(cuota.getFechaVencimiento());
     * response.setMontoCapital(cuota.getMontoCapital());
     * response.setMontoInteres(cuota.getMontoInteres());
     * response.setEstadoCuota(cuota.getEstadoCuota());
     * // ... otros campos
     * return response;
     * }
     */
}
