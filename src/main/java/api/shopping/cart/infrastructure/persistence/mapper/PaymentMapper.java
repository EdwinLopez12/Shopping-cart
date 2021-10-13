package api.shopping.cart.infrastructure.persistence.mapper;

import api.shopping.cart.domain.dto.payment.PaymentResponse;
import api.shopping.cart.domain.model.Link;
import api.shopping.cart.domain.utils.FormatDates;
import api.shopping.cart.infrastructure.persistence.entity.Payment;
import com.paypal.orders.LinkDescription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * PaymentMapper class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Component
public class PaymentMapper {

    private final OrderMapper orderMapper = new OrderMapper();

    public PaymentResponse paymentToPaymentResponse(Payment payment, List<LinkDescription> linkDescriptions) {
        PaymentResponse paymentResponse = new PaymentResponse();
        if (payment.getId() != null) paymentResponse.setId(payment.getId());
        if (payment.getPaypalOrderId() != null) paymentResponse.setPaypalOrderId(payment.getPaypalOrderId());
        if (payment.getTotalPayment() != null) paymentResponse.setTotalPayment(payment.getTotalPayment());
        if (payment.getDate() != null) paymentResponse.setDate(FormatDates.instantToString(payment.getDate()));
        if (payment.getPaymentMethod() != null) paymentResponse.setPaymentMethod(payment.getPaymentMethod());
        if (payment.getOrder() != null) {
            paymentResponse.setOrderResponse(orderMapper.orderToOrderResponse(payment.getOrder()));
        }
        if (linkDescriptions != null) {
            List<Link> links = new ArrayList<>();
            for (LinkDescription ld : linkDescriptions) {
                Link link = Link.builder()
                        .rel(ld.rel())
                        .href(ld.href())
                        .type(Link.HttpMethods.valueOf(ld.method()))
                        .build();
                links.add(link);
            }
            paymentResponse.setLinks(links);
        }
        return paymentResponse;
    }
}
