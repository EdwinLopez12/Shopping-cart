package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.payment.PaymentPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentRequest;
import api.shopping.cart.domain.dto.payment.PaymentResponse;
import api.shopping.cart.domain.exception.ApiConflictException;
import api.shopping.cart.domain.exception.ApiNotFoundException;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.repository.OrderRepository;
import api.shopping.cart.domain.repository.PaymentRepository;
import api.shopping.cart.domain.repository.ProductRepository;
import api.shopping.cart.domain.repository.UserDataRepository;
import api.shopping.cart.domain.usecase.PaymentService;
import api.shopping.cart.domain.usecase.UserService;
import api.shopping.cart.infrastructure.persistence.OrderStatus;
import api.shopping.cart.infrastructure.persistence.entity.OrderProduct;
import api.shopping.cart.infrastructure.persistence.entity.Payment;
import api.shopping.cart.infrastructure.persistence.entity.Product;
import api.shopping.cart.infrastructure.persistence.entity.User;
import api.shopping.cart.infrastructure.persistence.entity.UserData;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import api.shopping.cart.infrastructure.persistence.mapper.PaymentMapper;
import com.alibaba.fastjson.JSONObject;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Capture;
import com.paypal.orders.Item;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.Payer;
import com.paypal.orders.PurchaseUnit;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PaymentServiceImpl class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final String ORDER_NOT_FOUND = "The order doesn't exist or couldn't be found";
    private static final String PRODUCT_NOT_FOUND = "The order doesn't exist or couldn't be found";
    private static final String USER_NOT_FOUND = "The user doesn't exist or couldn't be found";
    private static final String PRODUCT_NOT_ENOUGH = "There are not enough products for : ";
    private static final String PAYMENT_METHOD_NO_VALID = "The payment method is not valid";
    private static final String PAYMENT_NO_EXIST = "The payment doesn't exist or couldn't be found";

    private final PaypalService paypalService;
    private final UserService userService;

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final UserDataRepository userDataRepository;

    private final GeneralResponseModelMapper generalMapper;
    private final PaymentMapper paymentMapper;


    @Transactional
    @Override
    public GeneralResponseModel addPayment(PaymentRequest paymentRequest) throws IOException {
        switch (paymentRequest.getPaymentMethod()) {
            // TODO: implement other payment methods
            case CASH:
            case CREDIT_CART:
            case DEBIT_CART:
                Payment payment = createPaymentObject(paymentRequest, OrderStatus.PAID);
                return generalMapper.responseToGeneralResponseModel(201, "add payment", "Payment paid successfully", Collections.singletonList(paymentMapper.paymentToPaymentResponse(payment, null)), "Ok");
            case PAYPAL:
                PaymentResponse paymentResponse = createOrder(paymentRequest);
                return generalMapper.responseToGeneralResponseModel(201, "add payment", "Payment pending", Collections.singletonList(paymentResponse), "Ok");
            default:
                throw new ApiConflictException(PAYMENT_METHOD_NO_VALID);
        }
    }

    private Payment createPaymentObject(PaymentRequest paymentRequest, OrderStatus orderStatus) {
        api.shopping.cart.infrastructure.persistence.entity.Order order = orderRepository.findById(paymentRequest.getOrderId()).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));

        List<Product> products = validateProductsExistence(order.getOrderProducts());
        UserData userData = getUserData(userService.getCurrentUser());

        Payment payment = new Payment();
        payment.setTotalPayment(order.getTotalPayment());
        payment.setOrder(order);
        payment.setUser(userData);
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setDate(Instant.now());
        if (!paymentRequest.getPaypalOrderId().isEmpty()) payment.setPaypalOrderId(paymentRequest.getPaypalOrderId());
        paymentRepository.save(payment);
        order.setStatus(orderStatus);
        orderRepository.save(order);
        if(orderStatus.toString().equals(OrderStatus.PAID.toString())) productRepository.saveAll(products);
        return payment;
    }

    private List<Product> validateProductsExistence(List<OrderProduct> orderProducts) {
        List<Product> products = new ArrayList<>();
        for (OrderProduct op : orderProducts) {
            Product product = productRepository.findById(op.getProduct().getId()).orElseThrow(() -> new ApiNotFoundException(PRODUCT_NOT_FOUND));
            if (op.getAmount() > product.getTotal()) throw new ApiConflictException(PRODUCT_NOT_ENOUGH + product.getName());
            product.setTotal(product.getTotal() - op.getAmount());
            products.add(product);
        }
        return products;
    }

    private UserData getUserData(User user) {
        return userDataRepository.findByUserId(user.getId()).orElseThrow(() -> new ApiNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public PaymentResponse createOrder(PaymentRequest paymentRequest) throws IOException {

        api.shopping.cart.infrastructure.persistence.entity.Order basicOrder = orderRepository.findById(paymentRequest.getOrderId()).orElseThrow(() -> new ApiNotFoundException(ORDER_NOT_FOUND));

        PayPalHttpClient client = paypalService.client;

        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody(basicOrder));

        HttpResponse<Order> response = client.execute(request);

        if (response.statusCode() == 201) {
            paymentRequest.setPaypalOrderId(response.result().id());
            Payment payment = createPaymentObject(paymentRequest, OrderStatus.PENDING);

            log.info("Status Code: " + response.statusCode());
            log.info("Status: " + response.result().status());
            log.info("Order ID: " + response.result().id());
            log.info("Intent: " + response.result().checkoutPaymentIntent());
            log.info("Links: ");
            for (LinkDescription link : response.result().links()) {
                log.info("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
            }
            log.info("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
                    + " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
            return paymentMapper.paymentToPaymentResponse(payment, response.result().links());
        }
        return null;
    }

    private OrderRequest buildRequestBody(api.shopping.cart.infrastructure.persistence.entity.Order order) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext().brandName("SHOPPING CART INC").landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();

        BigDecimal taxTotal = BigDecimal.valueOf(0.00);
        BigDecimal taxBase = BigDecimal.valueOf(1.00);

        List<Item> items = new ArrayList<>();

        for (OrderProduct op : order.getOrderProducts()) {
            taxTotal = taxTotal.add(taxBase.multiply(BigDecimal.valueOf(op.getAmount())));
            Item item = new Item()
                    .name(op.getProduct().getName())
                    .description(op.getProduct().getDescription())
                    .sku(op.getProduct().getCode())
                    .unitAmount(new Money().currencyCode("USD").value(op.getValue().toString()))
                    .tax(new Money().currencyCode("USD").value("1.00"))
                    .quantity(op.getAmount().toString());
            items.add(item);
        }

        BigDecimal shipping = BigDecimal.valueOf(30.00);
        BigDecimal handling = BigDecimal.valueOf(10.00);
        BigDecimal shippingDiscount = BigDecimal.valueOf(10.00);
        BigDecimal unitsValue = order.getTotalPayment();
        BigDecimal valueTotalWithTaxes = unitsValue.add(taxTotal).add(shipping).add(handling).subtract(shippingDiscount);

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().referenceId("PUHF")
                .description("DESCRIPTION").customId("CUST-ID").softDescriptor("DESCRIPTOR")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(valueTotalWithTaxes.toString())
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value(unitsValue.toString()))
                                .shipping(new Money().currencyCode("USD").value(shipping.toString()))
                                .handling(new Money().currencyCode("USD").value(handling.toString()))
                                .taxTotal(new Money().currencyCode("USD").value(taxTotal.toString()))
                                .shippingDiscount(new Money().currencyCode("USD").value(shippingDiscount.toString()))))
                .items(items)
                .shippingDetail(
                        new ShippingDetail()
                                .name(new Name().fullName("Shopping Cart"))
                                .addressPortable(new AddressPortable()
                                        .addressLine1("123 Townsend St")
                                        .addressLine2("Floor 6")
                                .adminArea2("San Francisco")
                                        .adminArea1("CA")
                                        .postalCode("94107")
                                        .countryCode("US")));

        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    @Override
    public GeneralResponseModel captureOrder(PaymentPaypalRequest paymentPaypalRequest) throws IOException {
        PayPalHttpClient client = paypalService.client;
        OrdersCaptureRequest request = new OrdersCaptureRequest(paymentPaypalRequest.getOrderPaypalId());
        request.requestBody(buildRequestBodyPayment());
        request.header("Authorization", "Bearer " + paymentPaypalRequest.getToken());

        HttpResponse<Order> response = client.execute(request);

        if (response.statusCode() == 201) {

            Payment payment = paymentRepository.findByPaypalOrderId(paymentPaypalRequest.getOrderPaypalId()).orElseThrow(() -> new ApiNotFoundException(PAYMENT_NO_EXIST));
            List<Product> products = validateProductsExistence(payment.getOrder().getOrderProducts());
            productRepository.saveAll(products);
            payment.getOrder().setStatus(OrderStatus.PAID);
            paymentRepository.save(payment);
            log.info("Status Code: " + response.statusCode());
            log.info("Status: " + response.result().status());
            log.info("Order ID: " + response.result().id());
            log.info("Links: ");
            for (LinkDescription link : response.result().links()) {
                log.info("\t" + link.rel() + ": " + link.href());
            }
            log.info("Capture ids:");
            for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
                for (Capture capture : purchaseUnit.payments().captures()) {
                    log.info("\t" + capture.id());
                }
            }
            log.info("Buyer: ");
            Payer buyer = response.result().payer();
            log.info("\tEmail Address: " + buyer.email());
            log.info("\tName: " + buyer.name().givenName() + " " + buyer.name().surname());
            return generalMapper.responseToGeneralResponseModel(200, "capture order", "Order captured", Collections.singletonList(paymentMapper.paymentToPaymentResponse(payment, response.result().links())), "Ok");
        }
        return null;
    }

    private OrderRequest buildRequestBodyPayment() {
        return new OrderRequest();
    }
}
