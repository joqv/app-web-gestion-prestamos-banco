package com.cibertec.mesaverde.domain.cuentas.repository;

import java.util.Optional;

import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;

public interface MonedaRepository {

    Optional<MonedaModel> findById(Long id);

}
