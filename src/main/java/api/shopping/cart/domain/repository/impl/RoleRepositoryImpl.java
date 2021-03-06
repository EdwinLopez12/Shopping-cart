package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.RoleRepository;
import api.shopping.cart.infrastructure.persistence.entity.Role;
import api.shopping.cart.infrastructure.persistence.jpa.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository.findByName(name);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleJpaRepository.save(role);
    }
}
