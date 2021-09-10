package api.carrito.compras.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;

    @ManyToMany(targetEntity = Privilege.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user){
        if(users == null){
            users = new ArrayList<>(Collections.singletonList(user));
        }else{
            users.add(user);
        }
    }

    /**
     * Remove user.
     *
     * @param user the user
     */
    public void removeUser(User user){
        if(users != null){
            users.remove(user);
        }
    }

    /**
     * Add privilege.
     *
     * @param privilege the privilege
     */
    public void addPrivilege(Privilege privilege){
        if(privileges == null){
            privileges = new ArrayList<>(Collections.singletonList(privilege));
        }else{
            privileges.add(privilege);
        }
    }

    /**
     * Remove privilege.
     *
     * @param privilege the privilege
     */
    public void removePrivilege(Privilege privilege){
        if(privileges != null){
            privileges.remove(privilege);
        }
    }
}
