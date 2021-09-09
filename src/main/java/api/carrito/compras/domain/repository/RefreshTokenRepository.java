package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    Optional<RefreshToken> findByToken(String token);
    RefreshToken save(RefreshToken refreshToken);
    void deleteByToken(String token);
}
