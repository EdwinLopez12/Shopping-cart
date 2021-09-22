package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProductJpaRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    @Query(
            value = "SELECT p FROM Product p WHERE p.id = :id AND p.deletedAt IS NULL"
    )
    Optional<Product> findById(Long id);

    @Query(
            value = "SELECT p FROM Product p WHERE p.name = :name AND p.deletedAt IS NULL"
    )
    Optional<Product> findByName(String name);
}
