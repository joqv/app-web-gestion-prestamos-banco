package com.cibertec.mesaverde.domain.prestamos.repository;

import java.util.List;
import java.util.Optional;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;

public interface PrestamoRepository {
    
    List<PrestamoModel> findAllPrestamos();

    PrestamoModel guardarPrestamo(PrestamoModel prestamo);

    Optional<PrestamoModel> findById(Long id);

    List<PrestamoModel> findByClienteId(Long clienteId);

    List<PrestamoModel> findByEstadoPrestamo(String estadoPrestamo);

    Boolean existsById(Long id);

    PrestamoModel update(Long id, String estadoActualizado);
}
