package api.carrito.compras.infrastructure.persistence.mapper;

import api.carrito.compras.domain.dto.privilege.PrivilegeRequest;
import api.carrito.compras.domain.dto.privilege.PrivilegeResponse;
import api.carrito.compras.infrastructure.persistence.entity.Privilege;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * PrivilegeMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class PrivilegeMapper {

    public PrivilegeResponse privilegeToPrivilegeResponse(Privilege privilege){
        PrivilegeResponse privilegeResponse = new PrivilegeResponse();
        if (privilege.getId() != null) privilegeResponse.setId(privilege.getId());
        if (privilege.getName() != null) privilegeResponse.setName(privilege.getName());
        return privilegeResponse;
    }

    public List<PrivilegeResponse> privilegeListToPrivilegeResponse(List<Privilege> privilegeList) {
        List<PrivilegeResponse> privileges = new ArrayList<>();
        for (Privilege privilege : privilegeList){
            privileges.add(privilegeToPrivilegeResponse(privilege));
        }
        return privileges;
    }
}
