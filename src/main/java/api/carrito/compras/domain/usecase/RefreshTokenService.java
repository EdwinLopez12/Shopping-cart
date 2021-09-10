package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;

/**
 * RefreshTokenService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();
    void validateRefreshToken(String token);
    void deleteRefreshToken(String token);
}
