package api.shopping.cart.domain.dto.payment;

import api.shopping.cart.infrastructure.persistence.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * PaymentRequest class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - oct. 2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    @JsonProperty
    private String paymentPaypalId;

    @JsonProperty
    @NotBlank(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @JsonProperty
    @NotNull(message = "Order is required")
    private Long orderId;
}
