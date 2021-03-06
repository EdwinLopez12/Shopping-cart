package api.shopping.cart.app.boot;

import api.shopping.cart.domain.repository.PrivilegeRepository;
import api.shopping.cart.domain.repository.RoleRepository;
import api.shopping.cart.domain.repository.UserRepository;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;
import api.shopping.cart.infrastructure.persistence.entity.Role;
import api.shopping.cart.infrastructure.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ApiUserRolesAndPrivilegesSetUpDataLoader class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */

@Component
@AllArgsConstructor
@Order(1)
public class ApiUserRolesAndPrivilegesSetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final PrivilegeRepository privilegeData;
    private final RoleRepository roleData;
    private final UserRepository userData;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Privilege browseRole = createPrivilegeIfNotFound("BROWSE_ROLE");
        Privilege readRole = createPrivilegeIfNotFound("READ_ROLE");
        Privilege editRole = createPrivilegeIfNotFound("EDIT_ROLE");
        Privilege addRole = createPrivilegeIfNotFound("ADD_ROLE");
        Privilege deleteRole = createPrivilegeIfNotFound("DELETE_ROLE");

        Privilege browsePrivilege = createPrivilegeIfNotFound("BROWSE_PRIVILEGE");
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");

        Privilege browseUser = createPrivilegeIfNotFound("BROWSE_USER");
        Privilege readUser = createPrivilegeIfNotFound("READ_USER");
        Privilege editUser = createPrivilegeIfNotFound("EDIT_USER");

        Privilege browseTown = createPrivilegeIfNotFound("BROWSE_TOWN");

        Privilege browseProduct = createPrivilegeIfNotFound("BROWSE_PRODUCT");
        Privilege readProduct = createPrivilegeIfNotFound("READ_PRODUCT");
        Privilege editProduct = createPrivilegeIfNotFound("EDIT_PRODUCT");
        Privilege addProduct = createPrivilegeIfNotFound("ADD_PRODUCT");
        Privilege deleteProduct = createPrivilegeIfNotFound("DELETE_PRODUCT");

        Privilege browseCategory = createPrivilegeIfNotFound("BROWSE_CATEGORY");
        Privilege readCategory = createPrivilegeIfNotFound("READ_CATEGORY");
        Privilege editCategory = createPrivilegeIfNotFound("EDIT_CATEGORY");
        Privilege addCategory = createPrivilegeIfNotFound("ADD_CATEGORY");
        Privilege deleteCategory = createPrivilegeIfNotFound("DELETE_CATEGORY");

        Privilege browseOrder = createPrivilegeIfNotFound("BROWSE_ORDER");
        Privilege readOrder = createPrivilegeIfNotFound("READ_ORDER");
        Privilege editOrder = createPrivilegeIfNotFound("EDIT_ORDER");
        Privilege addOrder = createPrivilegeIfNotFound("ADD_ORDER");
        Privilege deleteOrder = createPrivilegeIfNotFound("DELETE_ORDER");

        Privilege browsePayment = createPrivilegeIfNotFound("BROWSE_PAYMENT");
        Privilege readPayment = createPrivilegeIfNotFound("READ_PAYMENT");
        Privilege addPayment = createPrivilegeIfNotFound("ADD_PAYMENT");

        List<Privilege> adminPrivileges = Arrays.asList(
                browseRole, readRole, editRole, addRole, deleteRole,
                browsePrivilege, readPrivilege,
                browseUser, readUser, editUser,
                browseTown,
                browseProduct, readProduct, editProduct, addProduct, deleteProduct,
                browseCategory, readCategory, editCategory, addCategory, deleteCategory,
                browseOrder, readOrder, editOrder, addOrder, deleteOrder,
                browsePayment, readPayment, addPayment
        );

        List<Privilege> userPrivileges = Arrays.asList(
                readUser, editUser,
                browseTown
        );

        Role adminRole = createRoleIfNotFound("ADMIN_ROLE", adminPrivileges);
        Role userRole = createRoleIfNotFound("USER_ROLE", userPrivileges);

        createUserIfNotFound("ADMIN", adminRole);
        createUserIfNotFound("USER", userRole);
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Optional<Privilege> privilege = privilegeData.findByName(name);
        if (!privilege.isPresent()) {
            Privilege createPrivilege = Privilege.builder()
                    .name(name)
                    .build();
            privilegeData.save(createPrivilege);
            return createPrivilege;
        }else{
            return privilege.get();
        }
    }

    private Role createRoleIfNotFound(String name, List<Privilege> privileges) {
        Optional<Role> role = roleData.findByName(name);
        if(!role.isPresent()) {
            Role createRole = Role.builder()
                    .name(name)
                    .privileges(privileges)
                    .build();
            roleData.save(createRole);
            return createRole;
        }else{
            return role.get();
        }
    }

    private void createUserIfNotFound(String username, Role role) {
        Optional<User> user = userData.findByUsernameOptional(username);
        if (!user.isPresent()) {
            User createUser = User.builder()
                    .username(username)
                    .email(username + "@email.com")
                    .password(passwordEncoder.encode("123456"))
                    .roles(Collections.singletonList(role))
                    .isEnable(true)
                    .createdAt(Instant.now())
                    .build();
            userData.save(createUser);
        }
    }
}
