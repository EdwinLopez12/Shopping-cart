package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.payment.PaymentOrderRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;

import java.io.IOException;

/**
 * PaymentService interface
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
public interface PaymentService {

    GeneralResponseModel createOrder(String orderId) throws IOException;
    GeneralResponseModel captureOrder(PaymentOrderRequest paymentOrderRequest) throws IOException;
}
