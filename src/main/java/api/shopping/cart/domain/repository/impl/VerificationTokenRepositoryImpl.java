package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.VerificationTokenRepository;
import api.shopping.cart.infrastructure.persistence.entity.VerificationToken;
import api.shopping.cart.infrastructure.persistence.jpa.VerificationTokenJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * VerificationTokenRepositoryImpl interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
@AllArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

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
