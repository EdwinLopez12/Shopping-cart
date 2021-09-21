package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.jpa.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository class
 *
 * @author edwin.lopezb.1297
 * @project shoppincart
 * @since v1.0.0 - sep. 2021
 */
@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productJpaRepository.findAll(pageable);
    }
}
