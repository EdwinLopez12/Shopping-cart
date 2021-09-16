package api.shopping.cart.domain.repository.impl;

import api.shopping.cart.domain.repository.PrivilegeRepository;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;
import api.shopping.cart.infrastructure.persistence.jpa.PrivilegeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PrivilegeRepositoryImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */


@Repository
@AllArgsConstructor
public class PrivilegeRepositoryImpl implements PrivilegeRepository {

    private final PrivilegeJpaRepository privilegeJpaRepository;

    @Override
    public Page<Privilege> findAll(Pageable pageable) {
        return privilegeJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Privilege> findByName(String name) {
        return privilegeJpaRepository.findByName(name);
    }

    @Override
    public Privilege save(Privilege privilege) {
        return privilegeJpaRepository.save(privilege);
    }
}
