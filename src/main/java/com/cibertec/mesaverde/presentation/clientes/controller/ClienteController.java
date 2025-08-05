package com.cibertec.mesaverde.presentation.clientes.controller;

import com.cibertec.mesaverde.domain.clientes.model.ClienteModel;
import com.cibertec.mesaverde.domain.clientes.service.ClienteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operations related to clients")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping(value = "/lista")
    public List<ClienteModel> listarClientes() {
        return clienteService.listarClientes();
    }
}
