package api.shopping.cart.domain.dto.order;

import api.shopping.cart.domain.dto.product.ProductResponse;
import api.shopping.cart.domain.dto.user.UserDataResponse;
import api.shopping.cart.domain.dto.user.UserResponse;
import api.shopping.cart.infrastructure.persistence.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderResponse class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private String date;
    private BigDecimal totalPayment;
    List<ProductResponse> products;
    private UserDataResponse userData;
}
