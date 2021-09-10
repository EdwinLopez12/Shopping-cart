package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.Role;

import java.util.Optional;

/**
 * RoleRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface RoleRepository {

    Optional<Role> findByName(String name);
    Role save(Role createRole);
}
