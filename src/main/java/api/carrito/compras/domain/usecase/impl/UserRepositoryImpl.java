package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.usecase.UserDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserDataEntity {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
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
