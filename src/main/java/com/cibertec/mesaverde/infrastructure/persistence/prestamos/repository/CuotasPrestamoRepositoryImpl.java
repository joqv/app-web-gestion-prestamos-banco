package com.cibertec.mesaverde.infrastructure.persistence.prestamos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa.CuotasPrestamoRepositoryJpa;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper.CuotasPrestamoMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CuotasPrestamoRepositoryImpl implements CuotasPrestamoRepository {

    private final CuotasPrestamoRepositoryJpa cuotasPrestamoRepositoryJpa;
    private final CuotasPrestamoMapper cuotasMapper;
    @Override
    public List<CuotasPrestamoModel> findAllCuotasPrestamos() {
        List<CuotasPrestamoEntity> cuotas = cuotasPrestamoRepositoryJpa.findAll();
        return cuotas.stream()
                .map(cuotasMapper::toModel)
                .collect(Collectors.toList());
    }
    @Override
    public CuotasPrestamoModel save(CuotasPrestamoModel cuotas) {
        CuotasPrestamoEntity entity = cuotasMapper.toEntity(cuotas);
        CuotasPrestamoEntity savedEntity = cuotasPrestamoRepositoryJpa.save(entity);
        return cuotasMapper.toModel(savedEntity);
    }
    @Override
    public Optional<CuotasPrestamoModel> findById(Long id) {
        return cuotasPrestamoRepositoryJpa.findById(id)
                .map(cuotasMapper::toModel);
    }
    @Override
    public List<CuotasPrestamoModel> findByPrestamoId(Long prestamoId) {
        return cuotasPrestamoRepositoryJpa.findByPrestamoId(prestamoId)
                .stream()
                .map(cuotasMapper::toModel)
                .collect(Collectors.toList());
    }
    @Override
    public List<CuotasPrestamoModel> findByEstadoCuota(String estadoCuota) {
        return cuotasPrestamoRepositoryJpa.findByEstadoCuota(estadoCuota)
                .stream()
                .map(cuotasMapper::toModel)
                .collect(Collectors.toList());
    }
    @Override
    public List<CuotasPrestamoModel> fingByFechaVencimientoHasta(LocalDate fechaVencimiento) {
        return cuotasPrestamoRepositoryJpa.findByFechaVencimientoLessThanEqual(fechaVencimiento).stream()
                .map(cuotasMapper::toModel)
                .collect(Collectors.toList());
    }
    @Override
    public CuotasPrestamoModel marcarPagada(Long id, LocalDate fechaPago) {
        Optional<CuotasPrestamoEntity> optionalEntity = cuotasPrestamoRepositoryJpa.findById(id);
        if (optionalEntity.isPresent()) {
            CuotasPrestamoEntity entity = optionalEntity.get();
            entity.setEstadoCuota("PAGADA");
            entity.setFechaPago(fechaPago);
            CuotasPrestamoEntity updatedEntity = cuotasPrestamoRepositoryJpa.save(entity);
            return cuotasMapper.toModel(updatedEntity);
        }
        return null;
    }
    @Override
    public CuotasPrestamoModel update(Long id, String estadoActualizado) {
        Optional<CuotasPrestamoEntity> optionalEntity = cuotasPrestamoRepositoryJpa.findById(id);
        if (optionalEntity.isPresent()) {
            CuotasPrestamoEntity entity = optionalEntity.get();
            entity.setEstadoCuota(estadoActualizado);
            CuotasPrestamoEntity updatedEntity = cuotasPrestamoRepositoryJpa.save(entity);
            return cuotasMapper.toModel(updatedEntity);
        }
        return null; // or throw an exception if preferred
    }
    @Override
    public List<CuotasPrestamoModel> findByClienteDni(String numeroDocumento) {
        return cuotasPrestamoRepositoryJpa.findByPrestamoClienteNumeroDocumento(numeroDocumento)
            .stream()
            .map(cuotasMapper::toModel)
            .collect(Collectors.toList());
    }

}