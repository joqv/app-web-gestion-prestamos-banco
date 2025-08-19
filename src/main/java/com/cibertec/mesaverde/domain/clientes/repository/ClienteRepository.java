package com.cibertec.mesaverde.domain.clientes.repository;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    List<ClienteModel> findAllClientes();

    Optional<ClienteModel> findById(Long id);

    ClienteModel guardarCliente(ClienteModel clienteModel);
}
