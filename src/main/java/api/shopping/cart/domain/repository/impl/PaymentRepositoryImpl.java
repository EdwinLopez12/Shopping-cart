package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.PaymentRepository;
import api.shopping.cart.infrastructure.persistence.entity.Payment;
import api.shopping.cart.infrastructure.persistence.jpa.PaymentJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * PaymentRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Repository
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(payment);
    }
}
