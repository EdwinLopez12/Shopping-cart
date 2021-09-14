package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.role.RoleRequest;
import api.carrito.compras.domain.dto.role.RoleResponse;
import api.carrito.compras.domain.exception.ApiConflictException;
import api.carrito.compras.domain.exception.ApiNotFoundException;
import api.carrito.compras.domain.exception.PageableDataResponseModel;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.repository.RoleRepository;
import api.carrito.compras.domain.usecase.PrivilegeService;
import api.carrito.compras.domain.usecase.RoleService;
import api.carrito.compras.infrastructure.RoutesMapping;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.carrito.compras.infrastructure.persistence.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * RoleServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shippingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_NOT_FOUND = "The role doesn't exist or couldn't be found";
    private static final String ROLE_ALREADY_EXIST = "The role already exist";
    private static final String ROLE_HAS_USERS = "The role can't be deleted because it's used by at least one user. Try assigning a new role to the user (s) and try deleting again.";

    private final PrivilegeService privilegeService;
    private final RoleRepository roleRepository;
    private final GeneralResponseModelMapper generalMapper;
    private final RoleMapper roleMapper;

    @Override
    public PageableGeneralResponseModel getAllRoles(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Role> r = roleRepository.findAll(pageable);
        return pageable(r, "get all");
    }

    private PageableGeneralResponseModel pageable(Page<Role> r, String type){

        List<RoleResponse> privileges = roleMapper.roleListToRoleResponse(r.getContent());

        PageableDataResponseModel pageableData = generalMapper.pageableResponseToPageableDataResponseModel(type, "listed roles", privileges, r.getSize(), r.getTotalPages(), r.getTotalElements(), r.getNumber());

        return generalMapper.pageableResponseToPageableGeneralResponseModel(r.getPageable().hasPrevious(), r.getPageable().previousOrFirst().getPageNumber(), RoutesMapping.URL_ROLES_V1, pageableData);
    }

    private Role findRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(ROLE_NOT_FOUND));
    }

    @Override
    public GeneralResponseModel getRole(Long id) {
        Role role = findRole(id);
        return generalMapper.responseToGeneralResponseModel(200, "get role", "Role found", Collections.singletonList(roleMapper.roleToRolesResponse(role)), "Ok");
    }

    @Override
    public GeneralResponseModel editRole(Long id, RoleRequest roleRequest) {
        Role role = findRole(id);
        Optional<Role> optionalRole = roleRepository.findByName(roleRequest.getName());
        if (!role.getName().equals(roleRequest.getName()) && optionalRole.isPresent()) {
            throw new ApiConflictException(ROLE_ALREADY_EXIST);
        } else {
            List<Privilege> privileges = privilegeService.privilegesRequestToPrivilege(roleRequest.getPrivileges());
            role = roleMapper.roleRequestToRole(roleRequest, role, privileges);
            role.setUpdatedAt(Instant.now());
            roleRepository.save(role);
            return generalMapper.responseToGeneralResponseModel(200, "edit role", "Edited role", Collections.singletonList(roleMapper.roleToRolesResponse(role)), "Ok");
        }
    }

    @Override
    public GeneralResponseModel addRole(RoleRequest roleRequest) {
        Optional<Role> role = roleRepository.findByName(roleRequest.getName());
        if (role.isPresent()) {
            throw new ApiConflictException(ROLE_ALREADY_EXIST);
        } else {
            Role newRole = new Role();
            List<Privilege> privileges = privilegeService.privilegesRequestToPrivilege(roleRequest.getPrivileges());
            newRole = roleMapper.roleRequestToRole(roleRequest, newRole, privileges);
            newRole.setCreatedAt(Instant.now());
            roleRepository.save(newRole);
            return generalMapper.responseToGeneralResponseModel(200, "add role", "Role created", Collections.singletonList(roleMapper.roleToRolesResponse(newRole)), "Ok");
        }
    }

    @Override
    public GeneralResponseModel deleteRole(Long id) {
        Role role = findRole(id);
        List<User> usersRole = role.getUsers();
        if (usersRole.isEmpty()){
            role.setDeletedAt(Instant.now());
            roleRepository.save(role);
            return generalMapper.responseToGeneralResponseModel(200, "delete role", "Role deleted", null, "Ok");
        }else{
            throw new ApiConflictException(ROLE_HAS_USERS);
        }
    }
}
