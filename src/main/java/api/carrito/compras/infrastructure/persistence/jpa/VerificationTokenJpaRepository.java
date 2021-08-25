package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenJpaRepository extends JpaRepository<VerificationToken, Long> {

}
