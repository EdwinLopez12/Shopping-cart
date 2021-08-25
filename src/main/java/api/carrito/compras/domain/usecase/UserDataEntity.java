package api.carrito.compras.domain.usecase;

import api.carrito.compras.infrastructure.persistence.entity.User;

import java.util.Optional;

public interface UserDataEntity {

    Optional<User> findByUsername(String username);
}
