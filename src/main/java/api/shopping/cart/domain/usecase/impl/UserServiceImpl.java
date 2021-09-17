package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.role.RoleRequest;
import api.shopping.cart.domain.dto.user.UserDataRequest;
import api.shopping.cart.domain.dto.user.UserRolesRequest;
import api.shopping.cart.domain.exception.ApiConflictException;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.RoleRepository;
import api.shopping.cart.domain.repository.UserRepository;
import api.shopping.cart.domain.usecase.UserService;
import api.shopping.cart.infrastructure.persistence.entity.Role;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
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

    @Override
    public GeneralResponseModel addData(UserDataRequest userDataRequest, String username) {
        return null;
    }
}
