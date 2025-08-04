package com.cibertec.mesaverde.infrastructure.persistence.clientes.jpa;

import com.cibertec.mesaverde.infrastructure.persistence.clientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long> {
}
