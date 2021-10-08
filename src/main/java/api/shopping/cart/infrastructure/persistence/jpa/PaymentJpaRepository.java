package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PaymentJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    @Query(
            value = "SELECT p FROM Payment p WHERE p.paypalOrderId = :id"
    )
    Optional<Payment> findByPaypalOrderId(String id);
}
