package api.carrito.compras.domain.dto.privilege;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PrivilegeResponse class
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
public class PrivilegeResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;
}
