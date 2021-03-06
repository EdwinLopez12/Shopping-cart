package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.UserRepository;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsernameOptional(String username) {
        return userJpaRepository.findByUsernameOptional(username);
    }

    @Override
    public Optional<User> findByEmailOptional(String email) {
        return userJpaRepository.findByEmailOptional(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public String findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public String findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
