package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.PasswordResetRepository;
import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;
import api.carrito.compras.infrastructure.persistence.jpa.PasswordResetJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PasswordResetRepositoryImpl implements PasswordResetRepository {

    private final PasswordResetJpaRepository passwordResetJpaRepository;

    @Override
    public void save(PasswordReset passwordReset) {
        passwordResetJpaRepository.save(passwordReset);
    }
}
