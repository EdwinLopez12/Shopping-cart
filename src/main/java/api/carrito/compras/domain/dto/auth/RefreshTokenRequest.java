package api.carrito.compras.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshTokenRequest {

    @JsonProperty
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;

    @JsonProperty
    @NotBlank(message = "Username is required")
    private String username;

}