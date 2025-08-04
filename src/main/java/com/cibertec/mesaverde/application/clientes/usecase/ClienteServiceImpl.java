package com.cibertec.mesaverde.application.clientes.usecase;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.clientes.repository.ClienteRepository;
import com.cibertec.mesaverde.domain.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAllClientes();
    }
}
