package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * StateJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shippingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface StateJpaRepository extends JpaRepository<State, Long> {

    @Query(
            value = "SELECT s FROM State s WHERE s.country.name = :country"
    )
    List<State> findAllByCountry(String country);

    @Query(
            value = "SELECT s FROM State s WHERE s.id = :id"
    )
    Optional<State> findByStateId(Long id);
}
