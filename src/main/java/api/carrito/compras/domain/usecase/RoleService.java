package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.dto.role.RoleRequest;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.model.GeneralResponseModel;

/**
 * RoleService class
 *
 * @author edwin.lopezb.1297
 * @project shippingcart
 * @since v1.0.0 - sep. 2021
 */
public interface RoleService {
    PageableGeneralResponseModel getAllRoles(Integer page, Integer size);
    GeneralResponseModel getRole(Long id);
    GeneralResponseModel editRole(Long id, RoleRequest roleRequest);
    GeneralResponseModel addRole(RoleRequest roleRequest);
    GeneralResponseModel deleteRole(Long id);
}
