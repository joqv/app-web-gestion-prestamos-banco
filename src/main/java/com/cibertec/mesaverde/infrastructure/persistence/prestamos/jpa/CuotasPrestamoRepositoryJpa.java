package com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;

public interface CuotasPrestamoRepositoryJpa extends JpaRepository<CuotasPrestamoEntity, Long> {

    //Buscar por prestamos id
    List<CuotasPrestamoEntity> findByPrestamoId(Long prestamoId);

    // Buscar cuotas por estado (ej. "PAGADA", "PENDIENTE", "VENCIDA")
    List<CuotasPrestamoEntity> findByEstadoCuota(String estadoCuota);

    //Buscar por fecha de vencimiento
    List<CuotasPrestamoEntity> findByFechaVencimientoLessThanEqual(LocalDate fechaVencimiento);

    //Buscar todas las cuotas referente a un cliente
    List<CuotasPrestamoEntity> findByPrestamoClienteNumeroDocumento(String numeroDocumento);

    /* @Query("SELECT c FROM CuotasPrestamoEntity c WHERE c.prestamo.cliente.dni =:dni")
    List<CuotasPrestamoEntity> findByClienteDni(@Param("dni") String dni);    */

   /*  // Listar todas las cuotas de un préstamo específico ordenadas por número
    List<CuotasPrestamoEntity> findByPrestamoIdOrderByNumeroCuotaAsc(Long prestamoId);

    

    // Buscar cuotas vencidas hasta una fecha determinada
    List<CuotasPrestamoEntity> findByFechaVencimientoBeforeAndEstado(LocalDate fecha, String estado);

    // Contar cuotas pendientes de un préstamo
    long countByPrestamoIdAndEstado(Long prestamoId, String estado); */


}
