package api.carrito.compras.domain.data;

import api.carrito.compras.infrastructure.persistence.entity.User;

import java.util.Optional;

public interface UserData {

    Optional<User> findByUsername(String username);
}
