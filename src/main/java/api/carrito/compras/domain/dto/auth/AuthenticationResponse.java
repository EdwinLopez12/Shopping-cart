package api.carrito.compras.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    @JsonProperty
    private String authenticationToken;

    @JsonProperty
    private String refreshToken;

    @JsonProperty
    private String expiresAt;

    @JsonProperty
    private String username;
}
