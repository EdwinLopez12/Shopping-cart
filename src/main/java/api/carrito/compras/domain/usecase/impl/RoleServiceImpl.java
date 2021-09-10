package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.repository.RoleRepository;
import api.carrito.compras.domain.usecase.RoleService;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final GeneralResponseModelMapper generalMapper;
    private final RoleMapper roleMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        return null;
    }
}
