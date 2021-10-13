package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.user.UserDataRequest;
import api.shopping.cart.domain.dto.user.UserRolesRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.infrastructure.persistence.entity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface UserService {

    GeneralResponseModel addData(UserDataRequest userDataRequest);
    GeneralResponseModel updateRoles(UserRolesRequest userRolesRequest, Long id);
    GeneralResponseModel deleteRoles(UserRolesRequest userRolesRequest, Long id);
    @Transactional
    User getCurrentUser();
    GeneralResponseModel editData(UserDataRequest userDataRequest);
}
