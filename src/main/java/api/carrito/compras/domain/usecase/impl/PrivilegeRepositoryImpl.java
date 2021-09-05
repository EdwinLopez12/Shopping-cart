package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.usecase.PrivilegeDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.jpa.PrivilegeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PrivilegeRepositoryImpl implements PrivilegeDataEntity {

    private final PrivilegeJpaRepository privilegeJpaRepository;

    @Override
    public Optional<Privilege> findByName(String name) {
        return privilegeJpaRepository.findByName(name);
    }

    @Override
    public Privilege save(Privilege privilege) {
        return privilegeJpaRepository.save(privilege);
    }
}
