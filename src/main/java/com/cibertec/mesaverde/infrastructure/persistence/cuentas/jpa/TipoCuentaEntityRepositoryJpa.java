package com.cibertec.mesaverde.infrastructure.persistence.cuentas.jpa;

import com.cibertec.mesaverde.infrastructure.persistence.cuentas.entity.TipoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCuentaEntityRepositoryJpa extends JpaRepository<TipoCuentaEntity, Long> {
}
