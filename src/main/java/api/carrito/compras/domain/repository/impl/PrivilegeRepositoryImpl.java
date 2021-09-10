package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.PrivilegeRepository;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.jpa.PrivilegeJpaRepository;
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
