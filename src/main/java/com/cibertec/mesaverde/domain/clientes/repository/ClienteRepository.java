package com.cibertec.mesaverde.domain.clientes.repository;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;

import java.util.List;

public interface ClienteRepository {

    List<ClienteModel> findAllClientes();
}
