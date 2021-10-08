package api.shopping.cart.domain.dto.payment;

import api.shopping.cart.domain.dto.order.OrderResponse;
import api.shopping.cart.domain.dto.user.UserDataResponse;
import api.shopping.cart.infrastructure.persistence.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * PaymenResponse class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;
    private String paypalOrderId;
    private BigDecimal totalPayment;
    private String date;
    private PaymentMethod paymentMethod;
    private OrderResponse orderResponse;
}
