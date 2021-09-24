package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.product.ProductRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;

/**
 * ProductService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface ProductService {

    PageableGeneralResponseModel getAll(Integer page, Integer size);
    GeneralResponseModel get(Long id);
    GeneralResponseModel add(ProductRequest productRequest);
    GeneralResponseModel edit(ProductRequest productRequest, Long id);
    GeneralResponseModel delete(Long id);
    GeneralResponseModel categoriesByProduct(Long id);
    PageableGeneralResponseModel deletedList(Integer page, Integer size);
    GeneralResponseModel reactivate(Long id);
}
