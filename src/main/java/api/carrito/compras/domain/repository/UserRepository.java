package api.carrito.compras.domain.repository;

import api.carrito.compras.infrastructure.persistence.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsernameOptional(String username);
    Optional<User> findByEmailOptional(String email);
    String findByUsername(String username);
    String findByEmail(String email);
    User save(User user);
}
