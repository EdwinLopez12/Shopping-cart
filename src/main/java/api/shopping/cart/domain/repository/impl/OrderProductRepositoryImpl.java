package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.OrderProductRepository;
import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;
import api.shopping.cart.infrastructure.persistence.jpa.OrderProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * OrderProductRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
@AllArgsConstructor
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderProductJpaRepository orderProductJpaRepository;

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductJpaRepository.save(orderProduct);
    }
}
