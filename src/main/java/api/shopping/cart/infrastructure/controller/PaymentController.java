package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.payment.OrderPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.PaymentService;
import api.shopping.cart.infrastructure.RoutesMapping;
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

    @PostMapping
    public ResponseEntity<GeneralResponseModel> addPayment(@RequestBody PaymentRequest paymentRequest) throws IOException {

        return new ResponseEntity<>(paymentService.addPayment(paymentRequest), HttpStatus.CREATED);
    }

//    /**
//     * Payment response entity.
//     *
//     * @param orderPaypalRequest the order paypal request
//     * @return the response entity
//     * @throws IOException the io exception
//     */
//    @PostMapping("/order")
//    public ResponseEntity<GeneralResponseModel> createOrder(@RequestBody OrderPaypalRequest orderPaypalRequest) throws IOException {
//
//        return new ResponseEntity<>(paymentService.createOrder(orderPaypalRequest), HttpStatus.OK);
//    }

    /**
     * Capture order response entity.
     *
     * @param paymentPaypalRequest the payment order request
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/capture")
    public ResponseEntity<GeneralResponseModel> captureOrder(@RequestBody PaymentPaypalRequest paymentPaypalRequest) throws IOException {

        return new ResponseEntity<>(paymentService.captureOrder(paymentPaypalRequest), HttpStatus.OK);
    }
}
