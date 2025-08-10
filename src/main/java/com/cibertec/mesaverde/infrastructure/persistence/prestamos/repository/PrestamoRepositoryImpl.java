package com.cibertec.mesaverde.infrastructure.persistence.prestamos.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibertec.mesaverde.domain.prestamos.model.PrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.PrestamoRepository;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.PrestamoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa.PrestamoRepositoryJpa;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.mapper.PrestamoMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PrestamoRepositoryImpl implements PrestamoRepository {

   
    private final PrestamoRepositoryJpa prestamoRepositoryJpa;
    private final PrestamoMapper prestamoMapper;

    @Override
    public List<PrestamoModel> findAllPrestamos() {
        List<PrestamoEntity> prestamos = prestamoRepositoryJpa.findAll();
        System.out.println(prestamos);
        return prestamos.stream()
                .map(prestamoMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PrestamoModel save(PrestamoModel prestamo) {
        PrestamoEntity entity = prestamoMapper.toEntity(prestamo);
        PrestamoEntity savedEntity = prestamoRepositoryJpa.save(entity);
        return prestamoMapper.toModel(savedEntity);
    }

    @Override
    public Optional<PrestamoModel> findById(Long id) {
        return prestamoRepositoryJpa.findById(id)
                .map(prestamoMapper::toModel);
    }

    @Override
    public List<PrestamoModel> findByClienteId(Long clienteId) {
        List<PrestamoEntity> prestamos = prestamoRepositoryJpa.findByClienteId(clienteId);
        return prestamos.stream()
                .map(prestamoMapper::toModel)
                .collect(Collectors.toList());

    }

    @Override
    public List<PrestamoModel> findByEstadoPrestamo(String estadoPrestamo) {
        List<PrestamoEntity> prestamos = prestamoRepositoryJpa.findByEstadoPrestamo(estadoPrestamo);
        return prestamos.stream()
                .map(prestamoMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(Long id) {
        return prestamoRepositoryJpa.existsById(id);
    }

    @Override
    public PrestamoModel update(Long id, String estadoActualizado) {
        Optional<PrestamoEntity> optionalEntity = prestamoRepositoryJpa.findById(id);
        if (optionalEntity.isPresent()) {
            PrestamoEntity entity = optionalEntity.get();
            entity.setEstadoPrestamo(estadoActualizado);
            PrestamoEntity updatedEntity = prestamoRepositoryJpa.save(entity);
            return prestamoMapper.toModel(updatedEntity);
        }
        return null; // or throw an exception if preferred
    }


}
