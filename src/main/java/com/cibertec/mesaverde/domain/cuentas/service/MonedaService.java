package com.cibertec.mesaverde.domain.cuentas.service;

import java.util.Optional;

import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;

public interface MonedaService {

    Optional<MonedaModel> buscarPorId(Long id);
}
