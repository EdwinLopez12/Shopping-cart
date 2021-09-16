package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.RefreshToken;

import java.util.Optional;

/**
 * RefreshTokenRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface RefreshTokenRepository {

    Optional<RefreshToken> findByToken(String token);
    RefreshToken save(RefreshToken refreshToken);
    void deleteByToken(String token);
}
