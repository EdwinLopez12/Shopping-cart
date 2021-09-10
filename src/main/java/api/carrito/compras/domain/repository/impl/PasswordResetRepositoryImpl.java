package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.PasswordResetRepository;
import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;
import api.carrito.compras.infrastructure.persistence.jpa.PasswordResetJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PasswordResetRepositoryImpl implements PasswordResetRepository {

    private final PasswordResetJpaRepository passwordResetJpaRepository;


    @Override
    public Optional<PasswordReset> findByToken(String token) {
        return passwordResetJpaRepository.findByToken(token);
    }

    @Override
    public void deleteByEmail(String email) {
        passwordResetJpaRepository.deleteByEmail(email);
    }

    @Override
    public void save(PasswordReset passwordReset) {
        passwordResetJpaRepository.save(passwordReset);
    }
}
