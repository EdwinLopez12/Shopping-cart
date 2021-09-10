package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * RoleRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface RoleRepository {

    Page<Role> findAll(Pageable pageable);
    Optional<Role> findByName(String name);
    Optional<Role> findById(Long id);
    Role save(Role createRole);
    Integer countUserRoles(Long id);
    void deleteRole(Long id);
}
