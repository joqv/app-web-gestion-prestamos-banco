package com.cibertec.mesaverde.infrastructure.persistence.transacciones.repository;

import com.cibertec.mesaverde.domain.transacciones.model.MovimientoModel;
import com.cibertec.mesaverde.domain.transacciones.repository.MovimientoRepository;
import com.cibertec.mesaverde.infrastructure.mapper.MovimientoMapper;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.entity.MovimientoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.entity.TransaccionEntity;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.jpa.MovimientoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovimientoRepositoryImpl implements MovimientoRepository {

    private final MovimientoRepositoryJpa movimientoRepositoryJpa;
    private final MovimientoMapper movimientoMapper;

    @Override
    public MovimientoModel guardarMovimiento(MovimientoModel movimientoModel) {

        MovimientoEntity entity = movimientoMapper.toEntity(movimientoModel);

        MovimientoEntity  movimientoGuardado = movimientoRepositoryJpa.save(entity);

        return movimientoMapper.toModel(movimientoGuardado);
    }
}
