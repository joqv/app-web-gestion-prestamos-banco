package com.cibertec.mesaverde.infrastructure.persistence.cuentas.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.MonedaRepository;
import com.cibertec.mesaverde.infrastructure.mapper.MonedaMapper;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa.MonedaRepositoryJpa;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MonedaRepositoryImpl implements MonedaRepository{

    private final MonedaRepositoryJpa monedaRepositoryJpa;
    private final MonedaMapper monedaMapper;

    @Override
    public Optional<MonedaModel> findById(Long id) {
        return monedaRepositoryJpa.findById(id)
            .map(monedaMapper::toModel);
    }

}
