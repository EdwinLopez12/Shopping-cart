package api.shopping.cart.domain.dto.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StateResponse class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateResponse {

    private Long id;
    private String region;
    private String stateCodeDane;
    private String state;
    private String townCodeDane;
    private String town;
}
