package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    @Query(
            value = "SELECT r FROM Role r WHERE r.name = :name AND r.deletedAt IS NULL"
    )
    Optional<Role> findByName(String name);

    @Query(
            value = "SELECT r FROM Role r WHERE r.id = :id AND r.deletedAt IS NULL"
    )
    Optional<Role> findById(Long id);
}
