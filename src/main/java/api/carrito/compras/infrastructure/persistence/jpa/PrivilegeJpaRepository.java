package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeJpaRepository extends JpaRepository<Privilege, Long> {

    @Query(
            value = "SELECT p FROM Privilege p WHERE p.name = :name"
    )
    Optional<Privilege> findByName(String name);
}
