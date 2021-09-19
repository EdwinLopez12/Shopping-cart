package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.UserData;

import java.util.Optional;

/**
 * UserDataRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface UserDataRepository {

    Optional<UserData> findByUserId(Long id);
    void save(UserData userData);
}
