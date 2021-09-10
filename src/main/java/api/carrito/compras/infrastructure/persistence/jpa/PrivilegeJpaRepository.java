package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PrivilegeJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface PrivilegeJpaRepository extends JpaRepository<Privilege, Long> {

    @Query(
            value = "SELECT p FROM Privilege p WHERE p.name = :name"
    )
    Optional<Privilege> findByName(String name);

    Page<Privilege> findAll(Pageable pageable);
}
