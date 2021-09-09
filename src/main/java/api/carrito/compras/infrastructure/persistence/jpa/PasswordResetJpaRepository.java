package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PasswordResetJpaRepository extends JpaRepository<PasswordReset, Long> {

    @Query(
            value = "SELECT pr FROM PasswordReset pr WHERE pr.token = :token"
    )
    Optional<PasswordReset> findByToken(String token);

    @Transactional
    @Query(
            value = "DELETE FROM PasswordReset pr WHERE pr.email = :email"
    )
    void deleteByEmail(String email);
}
