package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDataJpaRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface UserDataJpaRepository extends JpaRepository<UserData, Long> {
}
