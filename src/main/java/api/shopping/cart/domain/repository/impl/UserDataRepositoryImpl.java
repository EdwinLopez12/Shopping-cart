package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.UserDataRepository;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import api.shopping.cart.infrastructure.persistence.jpa.UserDataJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * UserDataRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class UserDataRepositoryImpl implements UserDataRepository {

    private final UserDataJpaRepository userDataJpaRepository;

    @Override
    public void save(UserData userData) {
        userDataJpaRepository.save(userData);
    }
}
