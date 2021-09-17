package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.model.GeneralResponseModel;

/**
 * StateService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface StateService {

    GeneralResponseModel listTownsByCountry(String country);
}
