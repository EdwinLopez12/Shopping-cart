package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.OrderStatus;
import api.shopping.cart.infrastructure.persistence.entity.Order;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * OrderRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface OrderRepository {
    Page<Order> findAllByDeletedAtIsNull(Pageable pageable);
    Page<Order> findAllByDeletedAtIsNullAndUserData(Pageable pageable, UserData userData);
    void save(Order order);
    Optional<Order> findById(Long id);
    Order findByUserDataAndStatusIsPending(UserData userData, OrderStatus orderStatus);
}
