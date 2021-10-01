package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.repository.RefreshTokenRepository;
import api.shopping.cart.domain.usecase.RefreshTokenService;
import api.shopping.cart.infrastructure.persistence.entity.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * RefreshTokenServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

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
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        if (!refreshToken.isPresent()) throw new ApiNotFoundException(TOKEN_NOT_FOUND);
    }

    @Override
    public void deleteRefreshToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        if (!refreshToken.isPresent()) throw new ApiNotFoundException(TOKEN_NOT_FOUND);
        refreshTokenRepository.deleteByToken(token);
    }
}
