package com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.MonedaEntity;

public interface MonedaRepositoryJpa extends JpaRepository<MonedaEntity, Long>{

    

}
