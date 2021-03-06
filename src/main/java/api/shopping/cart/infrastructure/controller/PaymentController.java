package api.shopping.cart.infrastructure.controller;

import api.shopping.cart.domain.dto.payment.PaymentPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.PaymentService;
import api.shopping.cart.infrastructure.RoutesMapping;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * Add payment response entity.
     *
     * @param paymentRequest the payment request
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation("Add the order in PayPal")
    @PreAuthorize("hasAuthority('ADD_PAYMENT')")
    @PostMapping
    public ResponseEntity<GeneralResponseModel> addPayment(@RequestBody PaymentRequest paymentRequest) throws IOException {

        return new ResponseEntity<>(paymentService.addPayment(paymentRequest), HttpStatus.CREATED);
    }

    /**
     * Capture order response entity.
     *
     * @param paymentPaypalRequest the payment order request
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation("Capture the order in PayPal")
    @PreAuthorize("hasAuthority('ADD_PAYMENT')")
    @PostMapping("/capture")
    public ResponseEntity<GeneralResponseModel> captureOrder(@RequestBody PaymentPaypalRequest paymentPaypalRequest) throws IOException {

        return new ResponseEntity<>(paymentService.captureOrder(paymentPaypalRequest), HttpStatus.OK);
    }
}
