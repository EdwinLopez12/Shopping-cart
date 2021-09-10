package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;

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
