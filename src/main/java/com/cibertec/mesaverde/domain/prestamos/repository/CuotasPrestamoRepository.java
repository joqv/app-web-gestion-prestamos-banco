package com.cibertec.mesaverde.domain.prestamos.repository;

import java.util.List;

import com.cibertec.mesaverde.domain.prestamos.model.CuotasPrestamoModel;


public interface CuotasPrestamoRepository {

    List<CuotasPrestamoModel> findAllCuotasPrestamos();
}
