package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.order.OrderRequest;
import api.shopping.cart.domain.exception.PageableGeneralResponseModel;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.OrderRepository;
import api.shopping.cart.domain.usecase.OrderService;
import api.shopping.cart.domain.usecase.UserService;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final GeneralResponseModelMapper generalMapper;
    private final OrderMapper orderMapper;

    @Override
    public PageableGeneralResponseModel getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public PageableGeneralResponseModel getAllByUser(Integer page, Integer size) {
        return null;
    }

    @Override
    public GeneralResponseModel get(Long id) {
        return null;
    }

    @Override
    public GeneralResponseModel edit(Long id, OrderRequest orderRequest) {
        return null;
    }

    @Override
    public GeneralResponseModel add(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public GeneralResponseModel delete(Long id) {
        return null;
    }
}
