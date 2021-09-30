package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.OrderRepository;
import api.shopping.cart.infrastructure.persistence.entity.Order;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import api.shopping.cart.infrastructure.persistence.jpa.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * OrderRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Page<Order> findAllByDeletedAtIsNull(Pageable pageable) {
        return orderJpaRepository.findAllByDeletedAtIsNull(pageable);
    }

    @Override
    public Page<Order> findAllByDeletedAtIsNullAndUserData(Pageable pageable, UserData userData) {
        return orderJpaRepository.findAllByDeletedAtIsNullAndUserData(pageable, userData);
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(order);
    }
}
