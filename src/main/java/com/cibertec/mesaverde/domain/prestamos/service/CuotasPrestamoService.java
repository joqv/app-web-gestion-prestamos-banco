package com.cibertec.mesaverde.domain.prestamos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;

public interface CuotasPrestamoService {

    List<CuotasPrestamoModel> listarCuotasPrestamos();

    // Registrar nueva cuota
    CuotasPrestamoModel registrarCuota(CuotasPrestamoModel cuota);

    // Buscar una cuota por su ID
    Optional<CuotasPrestamoModel> buscarPorId(Long id);

    // Listar cuotas por préstamo
    List<CuotasPrestamoModel> listarPorPrestamo(Long prestamoId);

    // Actualizar estado o datos de una cuota
    CuotasPrestamoModel actualizarCuota(CuotasPrestamoModel cuota);

    // Registrar pago de una cuota
    CuotasPrestamoModel registrarPago(Long id, BigDecimal montoPagado);

    // Listar cuotas de un cliente por su dni
    List<CuotasPrestamoModel> buscarPorClienteDni(String tipoDocumento);

}
