package com.cibertec.mesaverde.domain.prestamos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;


public interface CuotasPrestamoRepository {

    List<CuotasPrestamoModel> findAllCuotasPrestamos();

    CuotasPrestamoModel save(CuotasPrestamoModel cuotas);

    Optional<CuotasPrestamoModel> findById(Long id);

    List<CuotasPrestamoModel> findByClienteDni(String numeroDocumento);

    List<CuotasPrestamoModel> findByPrestamoId(Long prestamoId);

    List<CuotasPrestamoModel> findByEstadoCuota(String estadoCuota);

    List<CuotasPrestamoModel> fingByFechaVencimientoHasta(LocalDate fechaVencimiento);

    CuotasPrestamoModel marcarPagada(Long id, LocalDate fechaPago);

    CuotasPrestamoModel update(Long id, String estadoActualizado);

}
