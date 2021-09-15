package api.carrito.compras.domain.dto.role;

import api.carrito.compras.domain.dto.privilege.PrivilegeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * RoleWithPrivilegesRequest class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleWithPrivilegesRequest {

    private RoleRequest name;

    private List<@Valid PrivilegeRequest> privileges;
}
