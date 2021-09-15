package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.dto.user.UserRolesRequest;
import api.carrito.compras.domain.model.GeneralResponseModel;

/**
 * UserService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface UserService {

    GeneralResponseModel updateRoles(UserRolesRequest userRolesRequest, Long id);
}
