package api.carrito.compras.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserRequest {

    @JsonProperty
    @NotBlank(message = "Username is required")
    @Length(min = 3, max = 255, message = "Username must be greater than or equal to 3")
    private String username;

    @JsonProperty
    @NotBlank(message = "Password is required")
    @Length(min = 6, max = 255, message = "Password must be greater than or equals to 6")
    private String password;
}
