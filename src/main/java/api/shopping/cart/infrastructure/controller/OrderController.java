package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.usecase.OrderService;
import api.shopping.cart.infrastructure.RoutesMapping;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_ORDERS_V1)
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


}
