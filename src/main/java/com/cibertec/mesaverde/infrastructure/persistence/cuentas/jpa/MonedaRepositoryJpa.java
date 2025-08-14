package com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa;

import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.BancoEntity;
import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.MonedaEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MonedaRepositoryJpa extends JpaRepository<MonedaEntity, Long> {

}
