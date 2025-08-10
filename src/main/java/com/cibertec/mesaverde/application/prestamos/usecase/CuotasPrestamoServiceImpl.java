package com.cibertec.mesaverde.application.prestamos.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.service.CuotasPrestamoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuotasPrestamoServiceImpl implements CuotasPrestamoService {

    private final CuotasPrestamoRepository cuotasPrestamoRepository;

    @Override
    public List<CuotasPrestamoModel> listarCuotasPrestamos() {
        return cuotasPrestamoRepository.findAllCuotasPrestamos();
    }

    @Override
    public CuotasPrestamoModel registrarCuota(CuotasPrestamoModel cuota) {
        return cuotasPrestamoRepository.save(cuota);
    }

    @Override
    public Optional<CuotasPrestamoModel> buscarPorId(Long id) {
        return cuotasPrestamoRepository.findById(id);
    }

    @Override
    public List<CuotasPrestamoModel> listarPorPrestamo(Long prestamoId) {
        return cuotasPrestamoRepository.findByPrestamoId(prestamoId);
    }

    @Override
    public CuotasPrestamoModel actualizarCuota(CuotasPrestamoModel cuota) {
        return cuotasPrestamoRepository.update(cuota.getId(), cuota.getEstadoCuota());
    }

    @Override
    @Transactional
    public CuotasPrestamoModel registrarPago(Long cuotaId, BigDecimal montoPagado) {
        // Buscar la cuota existente
        CuotasPrestamoModel cuota = cuotasPrestamoRepository.findById(cuotaId)
                .orElseThrow(() -> new RuntimeException("Cuota no encontrada"));

        // Registrar el pago
        cuota.setMontoPagado(montoPagado);
        cuota.setFechaPago(LocalDate.now());

        // Cambiar estado según monto pagado
        int comparacion = montoPagado.compareTo(cuota.getMontoTotalCuota());
        if (comparacion >= 0) {
            cuota.setEstadoCuota("PAGADA");
        } else if (montoPagado.compareTo(BigDecimal.ZERO) > 0) {
            cuota.setEstadoCuota("PARCIAL");
        }

        return cuotasPrestamoRepository.save(cuota);

    }

}
