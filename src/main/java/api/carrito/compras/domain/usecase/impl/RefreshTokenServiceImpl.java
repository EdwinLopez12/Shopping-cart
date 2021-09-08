package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.exception.ApiNotFoundException;
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

    private static final String TOKEN_NOT_FOUND = "Refresh token doesn't exist or could not be found";

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new ApiNotFoundException(TOKEN_NOT_FOUND));
    }
}
