package api.shopping.cart.infrastructure.controller;

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

    @PostMapping
    ResponseEntity<HttpResponse<Order>> payment() throws IOException {

        HttpResponse<Order> response = paymentService.createOrder();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
