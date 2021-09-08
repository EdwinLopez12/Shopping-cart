package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);
}
