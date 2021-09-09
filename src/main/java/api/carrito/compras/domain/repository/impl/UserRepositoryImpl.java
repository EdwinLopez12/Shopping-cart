package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.UserRepository;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsernameOptional(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmailOptional(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public String findUsername(String username) {
        return userJpaRepository.findUsername(username);
    }

    @Override
    public String findEmail(String email) {
        return userJpaRepository.findEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
