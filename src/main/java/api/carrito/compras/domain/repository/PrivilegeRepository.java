package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.Privilege;

import java.util.Optional;

public interface PrivilegeRepository {

    Optional<Privilege> findByName(String name);
    Privilege save(Privilege privilege);
}
