package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.repository.RefreshTokenRepository;
import api.carrito.compras.domain.usecase.RefreshTokenService;
import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .build();
        return refreshTokenRepository.save(refreshToken);
    }
}
