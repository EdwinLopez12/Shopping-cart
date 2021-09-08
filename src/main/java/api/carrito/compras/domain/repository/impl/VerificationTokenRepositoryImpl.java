package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.VerificationTokenDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import api.carrito.compras.infrastructure.persistence.jpa.VerificationTokenJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenDataEntity {

    private final VerificationTokenJpaRepository verificationTokenJpaRepository;


    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenJpaRepository.findByToken(token);
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenJpaRepository.save(verificationToken);
    }
}
