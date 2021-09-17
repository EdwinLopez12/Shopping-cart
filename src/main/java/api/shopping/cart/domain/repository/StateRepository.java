package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.State;

import java.util.List;

/**
 * StateRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface StateRepository {

    List<State> findAllByCountry(String country);
    void save(State state);
}
