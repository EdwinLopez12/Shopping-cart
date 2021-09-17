package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.State;

/**
 * StateRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface StateRepository {

    void save(State state);
}
