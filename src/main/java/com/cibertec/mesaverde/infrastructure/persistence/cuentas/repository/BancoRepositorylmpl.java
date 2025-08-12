package com.cibertec.mesaverde.infrastructure.persistence.cuentas.repository;

import com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa.BancoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class BancoRepositorylmpl implements BancoRepository {

    private final BancoRepositoryJpa bancoRepositoryJpa;
    private final BancoMapper bancoMapper;

    @Override
    public BancoModel
}