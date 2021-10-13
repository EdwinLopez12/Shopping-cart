package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.jpa.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Page<Product> findAllByDeletedAtIsNull(Pageable pageable) {
        return productJpaRepository.findAllByDeletedAtIsNull(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        productJpaRepository.saveAll(products);
    }

    @Override
    public Page<Product> findAllByDeletedAtIsNotNull(Pageable pageable) {
        return productJpaRepository.findAllByDeletedAtIsNotNull(pageable);
    }

    @Override
    public Optional<Product> findByIdAndDeleteAtIsNotNull(Long id) {
        return productJpaRepository.findByIdAndDeletedAtIsNotNull(id);
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productJpaRepository.findByCategoryId(id);
    }
}
