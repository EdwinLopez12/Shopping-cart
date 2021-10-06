package api.shopping.cart.domain.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PaymentOrderRequest class
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
public class PaymentOrderRequest {

    @JsonProperty
    private String orderPaypalId;
}
