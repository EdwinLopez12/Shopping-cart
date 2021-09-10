package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.privilege.PrivilegeResponse;
import api.carrito.compras.domain.dto.role.RoleResponse;
import api.carrito.compras.domain.exception.PageableDataResponseModel;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.repository.RoleRepository;
import api.carrito.compras.domain.usecase.RoleService;
import api.carrito.compras.infrastructure.RoutesMapping;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.carrito.compras.infrastructure.persistence.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Role> r = roleRepository.findAll(pageable);
        return pageable(r, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Role> r, String type){

        List<RoleResponse> privileges = roleMapper.roleListToRoleResponse(r.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed roles", privileges, r.getSize(), r.getTotalPages(), r.getTotalElements(), r.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(r.getPageable().hasPrevious(), r.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_ROLES_V1, pageableData);
    }
}
