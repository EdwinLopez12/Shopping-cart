package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.dto.role.RoleWithPrivilegesRequest;
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
    GeneralResponseModel editRole(Long id, RoleWithPrivilegesRequest roleWithPrivilegesRequest);
    GeneralResponseModel addRole(RoleWithPrivilegesRequest roleWithPrivilegesRequest);
    GeneralResponseModel deleteRole(Long id);
    GeneralResponseModel getAllUsers(Long id);
}
