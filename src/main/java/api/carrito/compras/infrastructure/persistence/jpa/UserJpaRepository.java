package api.carrito.compras.infrastructure.persistence.jpa;

import api.carrito.compras.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT u FROM User u WHERE u.username = :username"
    )
    Optional<User> findByUsername(String username);

    @Query(
            value = "SELECT u FROM User u WHERE u.email = :email"
    )
    Optional<User> findByEmail(String email);

    @Query(
            value = "SELECT u.username FROM User u WHERE u.username = :username"
    )
    String findUsername(String username);

    @Query(
            value = "SELECT u.email FROM User u WHERE u.email = :email"
    )
    String findEmail(String email);


}
