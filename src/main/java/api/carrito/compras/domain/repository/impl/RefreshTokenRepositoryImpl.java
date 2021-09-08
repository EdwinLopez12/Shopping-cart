package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.RefreshTokenRepository;
import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;
import api.carrito.compras.infrastructure.persistence.jpa.RefreshTokenJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }
}
