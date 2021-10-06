package api.shopping.cart.domain.usecase.impl;

import api.shopping.cart.domain.dto.payment.OrderPaypalRequest;
import api.shopping.cart.domain.dto.payment.PaymentPaypalRequest;
import api.shopping.cart.domain.model.GeneralResponseModel;
import api.shopping.cart.domain.usecase.PaymentService;
import api.shopping.cart.infrastructure.persistence.mapper.GeneralResponseModelMapper;
import com.alibaba.fastjson.JSONObject;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Capture;
import com.paypal.orders.Payer;
import com.paypal.orders.Item;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnit;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private final PaypalService paypalService;
    private final GeneralResponseModelMapper generalMapper;

    @Override
    public GeneralResponseModel createOrder(OrderPaypalRequest orderPaypalRequest) throws IOException {
        PayPalHttpClient client = paypalService.client;
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody());
        //3. Call PayPal to set up a transaction
        HttpResponse<Order> response = client.execute(request);
        if (true) {
            if (response.statusCode() == 201) {
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
                log.info("Full response body:");
                log.info(new JSONObject(Boolean.parseBoolean(new Json().serialize(response.result()))).toString());
            }
        }
        return generalMapper.responseToGeneralResponseModel(200, "create order", "Payment with paypal success", Collections.singletonList(response.result().purchaseUnits().toArray()), "Ok");
    }

    private OrderRequest buildRequestBody() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext().brandName("EXAMPLE INC").landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().referenceId("PUHF")
                .description("Sporting Goods").customId("CUST-HighFashions").softDescriptor("HighFashions")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value("230.00")
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value("180.00"))
                                .shipping(new Money().currencyCode("USD").value("30.00"))
                                .handling(new Money().currencyCode("USD").value("10.00"))
                                .taxTotal(new Money().currencyCode("USD").value("20.00"))
                                .shippingDiscount(new Money().currencyCode("USD").value("10.00"))))
                .items(new ArrayList<Item>() {
                    {
                        add(new Item().name("T-shirt").description("Green XL").sku("sku01")
                                .unitAmount(new Money().currencyCode("USD").value("90.00"))
                                .tax(new Money().currencyCode("USD").value("10.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                        add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
                                .unitAmount(new Money().currencyCode("USD").value("45.00"))
                                .tax(new Money().currencyCode("USD").value("5.00")).quantity("2")
                                .category("PHYSICAL_GOODS"));
                    }
                })
                .shippingDetail(new ShippingDetail().name(new Name().fullName("John Doe"))
                        .addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
                                .adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    private OrderRequest buildRequestBodyPayment() {
        return new OrderRequest();
    }

    @Override
    public GeneralResponseModel captureOrder(PaymentPaypalRequest paymentPaypalRequest) throws IOException {
        PayPalHttpClient client = paypalService.client;
        OrdersCaptureRequest request = new OrdersCaptureRequest(paymentPaypalRequest.getOrderPaypalId());
        request.requestBody(buildRequestBodyPayment());
        request.header("Authorization", "Bearer A21AALm_9fEkuAjYSPYyhmtODOaXtEGAOK8Mn7xqMDKYWO7UfngeLQRseMXCetjimkDSZBrVqPLn8ekq4nodQvCZG2TvArFaw");

        HttpResponse<Order> response = client.execute(request);
        log.info(response.toString());
        if (true) {
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
            log.info("Full response body:");
            log.info(new JSONObject(Boolean.parseBoolean(new Json().serialize(response.result()))).toString());
        }
        return generalMapper.responseToGeneralResponseModel(200, "capture order", "Order captured", Collections.singletonList(response.result()), "Ok");
    }
}
