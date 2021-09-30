package api.shopping.cart.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserDataResponse class
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
public class UserDataResponse {
    private Long id;
    private String name;
    private String lastName;
    private String cellphone;
    private String email;
    private String address;
    private String state;
    private String country;
}
