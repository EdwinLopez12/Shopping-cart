package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.Privilege;

import java.util.Optional;

public interface PrivilegeDataEntity {

    Optional<Privilege> findByName(String name);

    Privilege save(Privilege privilege);
}
