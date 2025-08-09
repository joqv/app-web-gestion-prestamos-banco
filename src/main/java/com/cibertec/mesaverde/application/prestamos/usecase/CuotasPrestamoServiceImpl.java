package com.cibertec.mesaverde.application.prestamos.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;
import com.cibertec.mesaverde.domain.prestamos.repository.CuotasPrestamoRepository;
import com.cibertec.mesaverde.domain.prestamos.service.CuotasPrestamoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuotasPrestamoServiceImpl implements CuotasPrestamoService {

    private final CuotasPrestamoRepository cuotasPrestamoRepository;

    @Override
    public List<CuotasPrestamoModel> listarCuotasPrestamos() {
        return cuotasPrestamoRepository.findAllCuotasPrestamos();
    }

}
