package com.cibertec.mesaverde.domain.clientes.service;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.presentation.clientes.dto.request.ClienteRequestDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<ClienteModel> listarClientes();

    ClienteModel procesarRegistroCliente(ClienteRequestDto dto);

    // Buscar una cuota por su ID
    Optional<ClienteModel> buscarPorId(Long id);
}
