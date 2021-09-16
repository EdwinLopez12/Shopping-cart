package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * VerificationTokenJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface VerificationTokenJpaRepository extends JpaRepository<VerificationToken, Long> {

    @Query(
            value = "SELECT v FROM VerificationToken v WHERE v.token = :token"
    )
    Optional<VerificationToken> findByToken(String token);
}
