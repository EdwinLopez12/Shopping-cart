package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.category.CategoryRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;

/**
 * CategoryService class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
public interface CategoryService {

    PageableGeneralResponseModel getAll(Integer page, Integer size);
    GeneralResponseModel get(Long id);
    GeneralResponseModel add(CategoryRequest categoryRequest);
    GeneralResponseModel edit(CategoryRequest categoryRequest, Long id);
    GeneralResponseModel delete(Long id);
}
