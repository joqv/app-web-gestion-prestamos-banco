package com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.PrestamoEntity;

public interface PrestamoRepositoryJpa extends JpaRepository<PrestamoEntity, Long> {

    // Listar todos los préstamos de un cliente específico
    List<PrestamoEntity> findByClienteId(Long clienteId);

    // Buscar préstamos por estado (ej. "APROBADO", "PENDIENTE", "CANCELADO")
    List<PrestamoEntity> findByEstadoPrestamo(String estadoPrestamo);
/* 
    // Buscar préstamos en un rango de fechas
    List<PrestamoEntity> findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Verificar si un cliente tiene préstamos activos
    boolean existsByClienteIdAndEstado(Long clienteId, String estado); */


}
