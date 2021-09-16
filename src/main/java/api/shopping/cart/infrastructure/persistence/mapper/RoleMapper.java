package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.privilege.PrivilegeResponse;
import api.shopping.cart.domain.dto.role.RoleWithPrivilegesRequest;
import api.shopping.cart.domain.dto.role.RoleResponse;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;
import api.shopping.cart.infrastructure.persistence.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleMapper class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */

@Component
public class RoleMapper {

    private final PrivilegeMapper privilegeMapper = new PrivilegeMapper();

    public RoleResponse roleToRolesResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        if (role.getId() != null) roleResponse.setId(role.getId());
        if (role.getName() != null) roleResponse.setName(role.getName());
        List<PrivilegeResponse> privileges = privilegeMapper.privilegeListToPrivilegeResponse(role.getPrivileges());
        if (!privileges.isEmpty()) roleResponse.setPrivileges(privileges);
        return roleResponse;
    }

    public List<RoleResponse> roleListToRoleResponse(List<Role> roleList) {
        List<RoleResponse> roles = new ArrayList<>();
        for (Role role : roleList){
            roles.add(roleToRolesResponse(role));
        }
        return roles;
    }

    public Role roleRequestToRole(RoleWithPrivilegesRequest roleWithPrivilegesRequest, Role role, List<Privilege> privileges) {
        if (roleWithPrivilegesRequest.getName() != null) role.setName(roleWithPrivilegesRequest.getName().getName());
        if (!privileges.isEmpty()) role.setPrivileges(privileges);
        return role;
    }
}
