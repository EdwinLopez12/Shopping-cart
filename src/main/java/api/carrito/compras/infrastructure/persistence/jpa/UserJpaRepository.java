package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserJpaRepository interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT u FROM User u WHERE u.username = :username"
    )
    Optional<User> findByUsernameOptional(String username);

    @Query(
            value = "SELECT u FROM User u WHERE u.email = :email"
    )
    Optional<User> findByEmailOptional(String email);

    @Query(
            value = "SELECT u.username FROM User u WHERE u.username = :username"
    )
    String findByUsername(String username);

    @Query(
            value = "SELECT u.email FROM User u WHERE u.email = :email"
    )
    String findByEmail(String email);


}
