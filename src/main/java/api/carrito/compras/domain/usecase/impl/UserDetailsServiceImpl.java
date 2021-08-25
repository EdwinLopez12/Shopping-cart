package api.carrito.compras.domain.usecase.impl;

import api.carrito.compras.domain.usecase.UserDataEntity;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import api.carrito.compras.infrastructure.persistence.entity.Role;
import api.carrito.compras.infrastructure.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDataEntity userData;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userData.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado al usuario: "+username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getIsEnable(),
                true, true, true,
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     * Se obtiene los privilegios de los roles
     * @param roles the roles list
     * @return privileges list
     */
    private List<String> getPrivileges(List<Role> roles){
        List<String> privileges = new ArrayList<>();
        List<Privilege> collectionPrivileges = new ArrayList<>();
        for(Role role : roles){
            collectionPrivileges.addAll(role.getPrivileges());
        }
        for(Privilege privilege : collectionPrivileges){
            privileges.add(privilege.getName());
        }
        return privileges;
    }

    /**
     * Se crea una lista de authorities a través de los privilegios
     * @param privileges the privileges name list
     * @return the authorities list
     */
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
