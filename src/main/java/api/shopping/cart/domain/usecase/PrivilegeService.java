package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.privilege.PrivilegeRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.infrastructure.persistence.entity.Privilege;

import java.util.List;

/**
 * PrivilegeService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */


public interface PrivilegeService {

    PageableGeneralResponseModel getAll(Integer page, Integer size);
    List<Privilege> privilegesRequestToPrivilege(List<PrivilegeRequest> privileges);
}
