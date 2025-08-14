package com.cibertec.mesaverde.infrastructure.persistence.cuentas.repository;

import com.cibertec.mesaverde.domain.model.BancoModel;
import com.cibertec.mesaverde.domain.repository.BancoRepository;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.BancoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa.BancoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BancoRepositoryImpl implements BancoRepository {

    private final BancoRepositoryJpa bancoRepositoryJpa;

    @Override
    public List<BancoModel> findAll() {
        return bancoRepositoryJpa.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BancoModel> findById(Long id) {
        return bancoRepositoryJpa.findById(id)
                .map(this::toModel);
    }

    @Override
    public BancoModel save(BancoModel banco) {
        BancoEntity entity = toEntity(banco);
        return toModel(bancoRepositoryJpa.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        bancoRepositoryJpa.deleteById(id);
    }

    // Conversión de Entity a Model
    private BancoModel toModel(BancoEntity entity) {
        return new BancoModel(entity.getId(), entity.getNombre());
    }

    // Conversión de Model a Entity
    private BancoEntity toEntity(BancoModel model) {
        return new BancoEntity(model.getId(), model.getNombre());
    }
}
