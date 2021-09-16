package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.dto.role.RoleRequest;
import api.carrito.compras.domain.dto.user.UserRolesRequest;
import api.carrito.compras.domain.exception.ApiConflictException;
import api.carrito.compras.domain.exception.ApiNotFoundException;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.repository.RoleRepository;
import api.carrito.compras.domain.repository.UserRepository;
import api.carrito.compras.domain.usecase.UserService;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import api.carrito.compras.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_ALREADY_ASSIGNED = "The email is already in use";
    private static final String ROLE_NOT_FOUND = "The role doesn't exist or couldn't be found";
    private static final String USER_NOT_FOUND = "The user doesn't exist or couldn't be found";
    private final GeneralResponseModelMapper generalMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public GeneralResponseModel updateRoles(UserRolesRequest userRolesRequest, Long id) {
        User user = findUser(id);
        if (userRolesRequest.getRole().size() == 1) {
                setRol(userRolesRequest.getRole().get(0), user);
        }else{
            setRoles(userRolesRequest.getRole(), user);
        }
        return generalMapper.responseToGeneralResponseModel(200, "update user roles", "Roles updated", null, "Ok");
    }

    @Override
    public GeneralResponseModel deleteRoles(UserRolesRequest userRolesRequest, Long id) {
        User user = findUser(id);
        if (userRolesRequest.getRole().size() == 1) {
            removeRole(userRolesRequest.getRole().get(0), user);
        }else{
            removeRoles(userRolesRequest.getRole(), user);
        }
        return generalMapper.responseToGeneralResponseModel(200, "update user roles", "Roles updated", null, "Ok");
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(USER_NOT_FOUND));
    }

    private Role findRole(RoleRequest roleRequest) {
        return roleRepository.findByName(roleRequest.getName()).orElseThrow(() -> new ApiNotFoundException(ROLE_NOT_FOUND));
    }

    private void setRol(RoleRequest roleRequest, User user) {
        Role role = findRole(roleRequest);
        verifyRoleInUser(role, user.getRoles());
        user.addRole(role);
        userRepository.save(user);
    }

    private void setRoles(List<RoleRequest> roleRequestList, User user) {
        for (RoleRequest roleRequest : roleRequestList){
            Role role = findRole(roleRequest);
            verifyRoleInUser(role, user.getRoles());
            user.addRole(role);
        }
        userRepository.save(user);
    }

    private void verifyRoleInUser(Role role, List<Role> roles) {
        for (Role rol : roles){
            if (rol.getName().equals(role.getName())) throw new ApiConflictException(ROLE_ALREADY_ASSIGNED);
        }
    }

    private void removeRole(RoleRequest roleRequest, User user) {
        Role role = findRole(roleRequest);
        user.removeRole(role);
        userRepository.save(user);
    }

    private void removeRoles(List<RoleRequest> roleRequestList, User user) {
        for (RoleRequest roleRequest : roleRequestList) {
            Role role = findRole(roleRequest);
            user.removeRole(role);
        }
        userRepository.save(user);
    }
}
