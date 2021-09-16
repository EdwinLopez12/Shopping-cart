package api.shopping.cart.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * PasswordResetRequest class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - sep. 2021
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResetRequest {

    @JsonProperty
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be in the correct format")
    private String email;

    @JsonProperty
    @NotBlank(message = "Password is required")
    @Length(min = 6, max = 255, message = "Password must be greater than or equals to 6")
    private String password;

    @JsonProperty
    @NotBlank(message = "Verification password is required")
    @Length(min = 6, max = 255, message = "Verification password must be greater than or equals to 6")
    private String passwordVerify;
}
