package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.User;

import java.util.Optional;

public interface UserDataEntity {

    Optional<User> findByUsernameOptional(String username);

    String findUsername(String username);

    String findEmail(String email);

    User save(User user);
}
