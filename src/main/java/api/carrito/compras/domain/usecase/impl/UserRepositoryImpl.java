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
}
