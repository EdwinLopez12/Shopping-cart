package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;

/**
 * OrderProductRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface OrderProductRepository {
    void save(OrderProduct orderProduct);
}
