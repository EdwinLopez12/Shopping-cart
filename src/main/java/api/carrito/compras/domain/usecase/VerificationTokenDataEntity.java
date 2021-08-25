package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;

public interface VerificationTokenDataEntity {

    VerificationToken save(VerificationToken verificationToken);
}
