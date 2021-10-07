package api.shopping.cart.domain.usecase;

import api.shopping.cart.domain.dto.payment.OrderPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentRequest;
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

    GeneralResponseModel addPayment(PaymentRequest paymentRequest);
    GeneralResponseModel createOrder(OrderPaypalRequest orderPaypalRequest) throws IOException;
    GeneralResponseModel captureOrder(PaymentPaypalRequest paymentPaypalRequest) throws IOException;
}
