package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * PrivilegeRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface PrivilegeRepository {

    Page<Privilege> findAll(Pageable pageable);
    Optional<Privilege> findByName(String name);
    Privilege save(Privilege privilege);
}
