package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.user.UserRolesRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;

/**
 * UserService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface UserService {

    GeneralResponseModel updateRoles(UserRolesRequest userRolesRequest, Long id);
    GeneralResponseModel deleteRoles(UserRolesRequest userRolesRequest, Long id);
}
