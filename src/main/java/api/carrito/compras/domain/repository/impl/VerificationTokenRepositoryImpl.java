package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.VerificationTokenDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import api.carrito.compras.infrastructure.persistence.jpa.VerificationTokenJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenDataEntity {

    private final VerificationTokenJpaRepository verificationTokenJpaRepository;

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenJpaRepository.save(verificationToken);
    }
}
