package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.Order;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * OrderJpaRepository class
 *
 * @author edwin.lopezb.1297
 * @project cart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query(
            value = "SELECT o FROM Order o WHERE o.deletedAt IS NULL"
    )
    Page<Order> findAllByDeletedAtIsNull(Pageable pageable);

    @Query(
            value = "SELECT o FROM Order o WHERE o.deletedAt IS NULL AND o.userData = :userData"
    )
    Page<Order> findAllByDeletedAtIsNullAndUserData(Pageable pageable, UserData userData);

    @Query(
            value = "SELECT o FROM Order o WHERE o.id = :id AND o.deletedAt IS NULL"
    )
    Optional<Order> findById(Long id);
}
