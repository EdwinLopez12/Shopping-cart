package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * PasswordResetJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface PasswordResetJpaRepository extends JpaRepository<PasswordReset, Long> {

    @Query(
            value = "SELECT pr FROM PasswordReset pr WHERE pr.token = :token"
    )
    Optional<PasswordReset> findByToken(String token);

    @Transactional
    void deleteByEmail(String email);
}
