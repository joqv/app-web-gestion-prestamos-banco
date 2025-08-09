package com.cibertec.mesaverde.infrastructure.persistence.prestamos.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.infrastructure.mapper.CuotasPrestamoMapper;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.entity.CuotasPrestamoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.prestamos.jpa.CuotasPrestamoRepositoryJpa;

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

}