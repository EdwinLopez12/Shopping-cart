package api.shopping.cart.domain.repository;


import api.shopping.cart.infrastructure.persistence.entity.PasswordReset;

import java.util.Optional;

/**
 * PasswordResetRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public interface PasswordResetRepository {

    Optional<PasswordReset> findByToken(String token);
    void deleteByEmail(String email);
    void save(PasswordReset passwordReset);
}
