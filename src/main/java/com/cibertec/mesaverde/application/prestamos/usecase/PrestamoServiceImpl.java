package com.cibertec.mesaverde.application.prestamos.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.repository.PrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.service.PrestamoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final CuotasPrestamoRepository cuotasPrestamoRepository;

    @Override
    public List<PrestamoModel> listarPrestamos() {
        return prestamoRepository.findAllPrestamos();
    }

    @Override
    @Transactional
    public PrestamoModel registrarPrestamo(PrestamoModel prestamo) {
        PrestamoModel prestamoGuardado = prestamoRepository.save(prestamo);
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
    double tasaInteresMensual = prestamo.getTasaInteres().doubleValue() / 100 / 12; // Convertir % anual a decimal mensual
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
}
