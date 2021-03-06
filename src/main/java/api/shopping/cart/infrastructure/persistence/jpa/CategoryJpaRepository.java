package api.shopping.cart.infrastructure.persistence.jpa;

import api.shopping.cart.infrastructure.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CategoryJpaRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByDeletedAtIsNotNull(Pageable pageable);

    @Query(
            value = "SELECT c FROM Category c WHERE c.id = :id AND c.deletedAt IS NULL"
    )
    Optional<Category> findById(Long id);

    @Query(
            value = "SELECT c FROM Category c WHERE c.name = :name AND c.deletedAt IS NULL"
    )
    Optional<Category> findByName(String name);

    @Query(
            value = "SELECT c FROM Product p JOIN p.categories c WHERE p.id = :id AND p.deletedAt IS NULL AND c.deletedAt IS NULL"
    )
    List<Category> findByProductId(Long id);

    Page<Category> findAllByDeletedAtIsNull(Pageable pageable);

    @Query(
            value = "SELECT c FROM Category c WHERE c.id = :id AND c.deletedAt IS NOT NULL"
    )
    Optional<Category> findByIdAndDeletedAtIsNotNull(Long id);
}
