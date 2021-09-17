package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StateJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shippingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface StateJpaRepository extends JpaRepository<State, Long> {

}
