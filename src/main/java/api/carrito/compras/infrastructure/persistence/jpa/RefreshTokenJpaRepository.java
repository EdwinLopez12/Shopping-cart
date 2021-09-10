package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    @Query(
            value = "SELECT r FROM RefreshToken r WHERE r.token = :token"
    )
    Optional<RefreshToken> findByToken(String token);


    @Transactional
    void deleteByToken(String token);
}
