package com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;

public interface CuotasPrestamoRepositoryJpa extends JpaRepository<CuotasPrestamoEntity, Long> {

    //Buscar por prestamos id
    List<CuotasPrestamoEntity> findByPrestamoId(Long prestamoId);

    // Buscar cuotas por estado (ej. "PAGADA", "PENDIENTE", "VENCIDA")
    List<CuotasPrestamoEntity> findByEstadoCuota(String estadoCuota);

    //Buscar por fecha de vencimiento
    List<CuotasPrestamoEntity> findByFechaVencimientoLessThanEqual(LocalDate fechaVencimiento);

   /*  // Listar todas las cuotas de un préstamo específico ordenadas por número
    List<CuotasPrestamoEntity> findByPrestamoIdOrderByNumeroCuotaAsc(Long prestamoId);

    

    // Buscar cuotas vencidas hasta una fecha determinada
    List<CuotasPrestamoEntity> findByFechaVencimientoBeforeAndEstado(LocalDate fecha, String estado);

    // Contar cuotas pendientes de un préstamo
    long countByPrestamoIdAndEstado(Long prestamoId, String estado); */


}
