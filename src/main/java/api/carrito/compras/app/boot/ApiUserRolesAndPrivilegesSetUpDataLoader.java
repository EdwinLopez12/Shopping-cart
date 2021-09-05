package api.carrito.compras.app.boot;

import api.carrito.compras.domain.usecase.PrivilegeDataEntity;
import api.carrito.compras.domain.usecase.RoleDataEntity;
import api.carrito.compras.domain.usecase.UserDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
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

@Component
@AllArgsConstructor
@Order(1)
public class ApiUserRolesAndPrivilegesSetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final PrivilegeDataEntity privilegeData;
    private final RoleDataEntity roleData;
    private final UserDataEntity userData;
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

        Privilege readUser = createPrivilegeIfNotFound("READ_USER");
        Privilege editUser = createPrivilegeIfNotFound("EDIT_USER");

        List<Privilege> adminPrivileges = Arrays.asList(
                browseRole, readRole, editRole, addRole, deleteRole,
                browsePrivilege, readPrivilege,
                readUser, editUser
        );

        List<Privilege> userPrivileges = Arrays.asList(
                readUser, editUser
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
        Optional<User> user = userData.findByUsername(username);
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
