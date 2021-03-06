package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * ProductRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface ProductRepository {

    Page<Product> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    void save(Product newProduct);

    void saveAll(List<Product> products);

    Page<Product> findAllByDeletedAtIsNotNull(Pageable pageable);

    Optional<Product> findByIdAndDeleteAtIsNotNull(Long id);

    List<Product> findByCategoryId(Long id);
}
