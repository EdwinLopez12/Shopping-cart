package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.Payment;

import java.util.Optional;

/**
 * PaymentRepository inteface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
public interface PaymentRepository {
    void save(Payment payment);
    Optional<Payment> findByPaypalOrderId(String id);
}
