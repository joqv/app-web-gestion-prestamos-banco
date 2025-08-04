package com.cibertec.mesaverde.infrastructure.persistence.transacciones.repository;

import com.cibertec.mesaverde.domain.transacciones.model.TransaccionModel;
import com.cibertec.mesaverde.infrastructure.persistence.transacciones.jpa.TransaccionRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransaccionRepositoryImpl {

    private final TransaccionRepositoryJpa transaccionRepositoryJpa;

//    public List<TransaccionModel> findAllTransacciones(){
//        return transaccionRepositoryJpa.findAll();
//    }
}
