package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * RefreshTokenJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    @Query(
            value = "SELECT r FROM RefreshToken r WHERE r.token = :token"
    )
    Optional<RefreshToken> findByToken(String token);


    @Transactional
    void deleteByToken(String token);
}
