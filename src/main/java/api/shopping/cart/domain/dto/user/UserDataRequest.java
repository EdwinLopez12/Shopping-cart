package api.shopping.cart.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * UserDataRequest class
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataRequest {

    @JsonProperty
    @NotBlank(message = "Nid es required")
    @Length(min = 9, max = 255, message = "Nid must be greater than or equal to 9")
    private String nid;

    @JsonProperty
    @NotBlank(message = "Name es required")
    @Length(min = 3, max = 255, message = "Name must be greater than or equal to 3")
    private String name;

    @JsonProperty
    @NotBlank(message = "Last name es required")
    @Length(min = 3, max = 255, message = "Last name must be greater than or equal to 3")
    private String lastName;

    // regex
    @JsonProperty
    @NotBlank(message = "Cellphone es required")
    private String cellphone;

    @JsonProperty
    @NotBlank(message = "Address es required")
    private String address;

    @JsonProperty
    @NotBlank(message = "Town es required")
    private Long town;
}
