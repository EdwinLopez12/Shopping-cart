package api.carrito.compras.domain.usecase;

import api.carrito.compras.domain.exception.PageableGeneralResponseModel;

/**
 * RoleService class
 *
 * @author edwin.lopezb.1297
 * @project compras
 * @since v1.0.0 - sep. 2021
 */
public interface RoleService {
    PageableGeneralResponseModel getAll(Integer page, Integer size);
}
