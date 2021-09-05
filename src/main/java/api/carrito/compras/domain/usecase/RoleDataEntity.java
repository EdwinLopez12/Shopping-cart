package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.Role;

import java.util.Optional;

public interface RoleDataEntity {

    Optional<Role> findByName(String name);

    Role save(Role createRole);
}
