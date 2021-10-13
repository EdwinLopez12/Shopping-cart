package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.order.OrderResponse;
import api.shopping.cart.domain.dto.product.ProductResponse;
import api.shopping.cart.domain.utils.FormatDates;
import api.shopping.cart.infrastructure.persistence.entity.Order;
import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Component
public class OrderMapper {

    private final ProductMapper productMapper = new ProductMapper();
    private final UserMapper userMapper = new UserMapper();

    public OrderResponse orderToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        if (order.getId() != null) orderResponse.setId(order.getId());
        if (order.getStatus() != null) orderResponse.setStatus(order.getStatus());
        if (order.getTotalPayment() != null) orderResponse.setTotalPayment(order.getTotalPayment());
        if (order.getUserData() != null) {
            orderResponse.setUserData(userMapper.userDataToUserResponse(order.getUserData()));
        }
        if (order.getCreatedAt() != null) orderResponse.setDate(FormatDates.instantToString(order.getCreatedAt()));
        if (!order.getOrderProducts().isEmpty()) {
            List<ProductResponse> productResponses = new ArrayList<>();
            for (OrderProduct orderProduct : order.getOrderProducts()) {
                orderProduct.getProduct().setTotal(orderProduct.getAmount());
                orderProduct.getProduct().setPrice(orderProduct.getValue());
                productResponses.add(productMapper.productToProductResponse(orderProduct.getProduct()));
            }
            orderResponse.setProducts(productResponses);
        }

        return orderResponse;
    }

    public List<OrderResponse> orderListToOrderResponse(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(orderToOrderResponse(order));
        }
        return orderResponses;
    }
}
