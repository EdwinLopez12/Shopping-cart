package api.shopping.cart.domain.usecase;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

import java.io.IOException;

/**
 * PaymentService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
public interface PaymentService {

    HttpResponse<Order> createOrder() throws IOException;
}
