package com.cibertec.mesaverde.application.prestamos.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.clientes.repository.ClienteRepository;
import com.cibertec.mesaverde.domain.cuentas.model.CuentaBancariaModel;
import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.CuentaBancariaRepository;
import com.cibertec.mesaverde.domain.cuentas.repository.MonedaRepository;
import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.repository.PrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.service.PrestamoService;
import com.cibertec.mesaverde.presentation.prestamos.dto.request.PrestamoRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final CuotasPrestamoRepository cuotasPrestamoRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final ClienteRepository clienteRepository;
    private final MonedaRepository monedaRepository;

    @Override
    public List<PrestamoModel> listarPrestamos() {
        return prestamoRepository.findAllPrestamos();
    }

    @Override
    @Transactional
    public PrestamoModel registrarPrestamo(PrestamoRequestDto prestamo) {

        // Validaciones
        if (prestamo.getCliente() == null) {
            throw new IllegalArgumentException("El ID del cliente es requerido para registrar el préstamo");
        }

        // Obtener entidades relacionadas
        ClienteModel cliente = clienteRepository.findById(prestamo.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + prestamo.getCliente()));

        CuentaBancariaModel cuenta = cuentaBancariaRepository.obtenerCuentaBancaria(prestamo.getCuentaDesembolso());

        MonedaModel moneda = monedaRepository.findById(prestamo.getMoneda())
                .orElseThrow(() -> new RuntimeException("Moneda no encontrada con ID: " + prestamo.getMoneda()));

        // 🔥 CREAR EL MODELO MANUALMENTE
        PrestamoModel prestamoModel = new PrestamoModel();

        // Asignar campos básicos
        prestamoModel.setMontoPrincipal(prestamo.getMontoPrincipal());
        prestamoModel.setTasaInteres(prestamo.getTasaInteres());
        prestamoModel.setPlazoMeses(prestamo.getPlazoMeses());
        prestamoModel.setFechaInicio(prestamo.getFechaInicio());
        prestamoModel.setFechaFinEstimada(prestamo.getFechaFinEstimada());
        prestamoModel.setEstadoPrestamo(prestamo.getEstadoPrestamo());

        // Asignar entidades relacionadas
        prestamoModel.setCliente(cliente);
        prestamoModel.setCuentaDesembolso(cuenta);
        prestamoModel.setMoneda(moneda);

        // Calcular campos derivados
        BigDecimal montoCuotaMensual = calcularCuotaMensual(
                prestamo.getMontoPrincipal(),
                prestamo.getTasaInteres(),
                prestamo.getPlazoMeses());
        prestamoModel.setMontoCuotaMensual(montoCuotaMensual);
        prestamoModel.setSaldoPendiente(prestamo.getMontoPrincipal());

        // Guardar préstamo
        PrestamoModel prestamoGuardado = prestamoRepository.guardarPrestamo(prestamoModel);

        // Generar cuotas
        generarCuotas(prestamoGuardado);

        return prestamoGuardado;
    }

    @Override
    public Optional<PrestamoModel> buscarPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public List<PrestamoModel> listarPorCliente(Long clienteId) {
        return prestamoRepository.findByClienteId(clienteId);
    }

    @Override
    public PrestamoModel actualizarPrestamo(PrestamoModel prestamo) {
        return prestamoRepository.update(prestamo.getId(), prestamo.getEstadoPrestamo());
    }

    private void generarCuotas(PrestamoModel prestamo) {
        double montoPrincipal = prestamo.getMontoPrincipal().doubleValue();
        double tasaInteresMensual = prestamo.getTasaInteres().doubleValue() / 100 / 12; // Convertir % anual a decimal
                                                                                        // mensual
        int plazoMeses = prestamo.getPlazoMeses();
        LocalDate fechaVencimiento = prestamo.getFechaInicio();

        // Cuota fija mensual (fórmula simplificada)
        double cuotaFija;
        if (tasaInteresMensual > 0) {
            double factor = Math.pow(1 + tasaInteresMensual, plazoMeses);
            cuotaFija = (montoPrincipal * tasaInteresMensual * factor) / (factor - 1);
        } else {
            cuotaFija = montoPrincipal / plazoMeses; // Si no hay interés
        }

        double saldoPendiente = montoPrincipal;

        for (int i = 1; i <= plazoMeses; i++) {
            // Calcular interés sobre saldo pendiente
            double montoInteres = saldoPendiente * tasaInteresMensual;

            // Calcular capital
            double montoCapital = cuotaFija - montoInteres;

            // Ajustar última cuota para eliminar diferencias de redondeo
            if (i == plazoMeses) {
                montoCapital = saldoPendiente;
                cuotaFija = montoCapital + montoInteres;
            }

            CuotasPrestamoModel cuota = CuotasPrestamoModel.builder()
                    .prestamo(prestamo)
                    .numeroCuota(i)
                    .fechaVencimiento(fechaVencimiento.plusMonths(i))
                    .montoCapital(BigDecimal.valueOf(montoCapital).setScale(2, RoundingMode.HALF_UP))
                    .montoInteres(BigDecimal.valueOf(montoInteres).setScale(2, RoundingMode.HALF_UP))
                    .montoTotalCuota(BigDecimal.valueOf(cuotaFija).setScale(2, RoundingMode.HALF_UP))
                    .montoPagado(BigDecimal.valueOf(0))
                    .fechaPago(null)
                    .estadoCuota("PENDIENTE")
                    .movimientoPago(null)
                    .build();

            cuotasPrestamoRepository.save(cuota);

            // Actualizar saldo
            saldoPendiente = saldoPendiente - montoCapital;
        }
    }

    private BigDecimal calcularCuotaMensual(BigDecimal montoPrincipal, BigDecimal tasaInteres, Integer plazoMeses) {
        double principal = montoPrincipal.doubleValue();
        double tasaInteresMensual = tasaInteres.doubleValue() / 100 / 12;
        int plazo = plazoMeses;

        double cuotaFija;
        if (tasaInteresMensual > 0) {
            double factor = Math.pow(1 + tasaInteresMensual, plazo);
            cuotaFija = (principal * tasaInteresMensual * factor) / (factor - 1);
        } else {
            cuotaFija = principal / plazo;
        }

        return BigDecimal.valueOf(cuotaFija).setScale(2, RoundingMode.HALF_UP);
    }

}
