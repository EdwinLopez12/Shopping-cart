package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.payment.PaymentOrderRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.PaymentService;
import api.shopping.cart.infrastructure.RoutesMapping;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * PaymentController class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@RestController
@RequestMapping(value = RoutesMapping.URL_PAYMENTS_V1)
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Payment response entity.
     *
     * @param orderId the order id
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/order")
    ResponseEntity<GeneralResponseModel> createOrder(@RequestBody String orderId) throws IOException {

        return new ResponseEntity<>(paymentService.createOrder(orderId), HttpStatus.OK);
    }

    /**
     * Capture order response entity.
     *
     * @param orderPaymentId the order payment id
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/capture")
    ResponseEntity<GeneralResponseModel> captureOrder(@RequestBody PaymentOrderRequest paymentOrderRequest) throws IOException {

        return new ResponseEntity<>(paymentService.captureOrder(paymentOrderRequest), HttpStatus.OK);
    }
}
