package com.cibertec.mesaverde.domain.prestamos.service;

import java.util.List;
import java.util.Optional;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.presentation.prestamos.dto.request.PrestamoRequestDto;

public interface PrestamoService {

        List<PrestamoModel> listarPrestamos();

        // Registrar un nuevo préstamo
        PrestamoModel registrarPrestamo(PrestamoRequestDto prestamo);

        // Buscar un préstamo por su ID
        Optional<PrestamoModel> buscarPorId(Long id);

        // Listar todos los préstamos de un cliente específico
        List<PrestamoModel> listarPorCliente(Long clienteId);

        // Actualizar información del préstamo (por ejemplo, estado)
        PrestamoModel actualizarPrestamo(PrestamoModel prestamo);

}
