package api.shopping.cart.domain.repository;

import api.shopping.cart.infrastructure.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CategoryRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface CategoryRepository {

    Page<Category> findAll(Pageable pageable);
}
