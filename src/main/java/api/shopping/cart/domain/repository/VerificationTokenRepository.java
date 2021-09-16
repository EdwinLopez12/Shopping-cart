package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.VerificationToken;

import java.util.Optional;

/**
 * VerificationTokenRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface VerificationTokenRepository {

    Optional<VerificationToken> findByToken(String token);
    VerificationToken save(VerificationToken verificationToken);
}
