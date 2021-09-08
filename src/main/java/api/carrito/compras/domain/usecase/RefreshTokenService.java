package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();
}
