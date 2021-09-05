package api.carrito.compras.domain.repository.impl;

import api.carrito.compras.domain.repository.RoleDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.jpa.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleDataEntity {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleJpaRepository.save(role);
    }
}
