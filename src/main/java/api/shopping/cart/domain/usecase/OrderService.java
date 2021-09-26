package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.order.OrderRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;

/**
 * OrderService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface OrderService {
    PageableGeneralResponseModel getAll(Integer page, Integer size);
    PageableGeneralResponseModel getAllByUser(Integer page, Integer size);
    GeneralResponseModel get(Long id);
    GeneralResponseModel edit(Long id, OrderRequest orderRequest);
    GeneralResponseModel add(OrderRequest orderRequest);
    GeneralResponseModel delete(Long id);
}
