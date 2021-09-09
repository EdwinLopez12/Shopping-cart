package api.carrito.compras.domain.repository;


import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;

import java.util.Optional;

public interface PasswordResetRepository {

    Optional<PasswordReset> findByToken(String token);
    void deleteByEmail(String email);
    void save(PasswordReset passwordReset);
}
