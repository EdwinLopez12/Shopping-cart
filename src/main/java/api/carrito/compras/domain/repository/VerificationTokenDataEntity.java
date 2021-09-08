package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;

import java.util.Optional;

public interface VerificationTokenDataEntity {

    Optional<VerificationToken> findByToken(String token);
    VerificationToken save(VerificationToken verificationToken);
}
