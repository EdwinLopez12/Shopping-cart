package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.StateRepository;
import api.shopping.cart.infrastructure.persistence.entity.State;
import api.shopping.cart.infrastructure.persistence.jpa.StateJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * StateRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
@AllArgsConstructor
public class StateRepositoryImpl implements StateRepository {

    private final StateJpaRepository stateJpaRepository;

    @Override
    public void save(State state) {
        stateJpaRepository.save(state);
    }
}
