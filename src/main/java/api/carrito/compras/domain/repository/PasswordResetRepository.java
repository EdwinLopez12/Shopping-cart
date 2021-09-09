package api.carrito.compras.domain.repository;


import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;

public interface PasswordResetRepository {

    void save(PasswordReset passwordReset);
}
