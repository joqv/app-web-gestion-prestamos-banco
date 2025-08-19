package com.cibertec.mesaverde.application.cuentas.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cibertec.mesaverde.domain.cuentas.model.MonedaModel;
import com.cibertec.mesaverde.domain.cuentas.repository.MonedaRepository;
import com.cibertec.mesaverde.domain.cuentas.service.MonedaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonedaServiceImpl implements MonedaService{

    private final MonedaRepository monedaRepository;


    @Override
    public Optional<MonedaModel> buscarPorId(Long id) {
        return monedaRepository.findById(id);
    }

   

}
