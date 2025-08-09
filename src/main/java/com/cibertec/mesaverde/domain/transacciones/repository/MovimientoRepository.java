package com.cibertec.mesaverde.domain.transacciones.repository;

import com.cibertec.mesaverde.domain.transacciones.model.MovimientoModel;

public interface MovimientoRepository {

    MovimientoModel guardarMovimiento(MovimientoModel movimientoModel);
}
