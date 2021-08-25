package api.carrito.compras.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserRequest {

    @JsonProperty
    @NotBlank(message = "El nombre de usuario es requerido")
    @Length(min = 3, max = 255, message = "El nombre de usuario debe de ser mayor o igual a 3")
    private String username;

    @JsonProperty
    @NotBlank(message = "El email es requerido")
    @Email(message = "El correo electrónico debe tener el formato correcto")
    private String email;

    @JsonProperty
    @NotBlank(message = "La contraseña es requerida")
    @Length(min = 6, max = 255, message = "La contraseña debe de ser mayor o igual a 6")
    private String password;

    @JsonProperty
    @NotBlank(message = "La verificación de contraseña es requerida")
    @Length(min = 6, max = 255, message = "La verificación de contraseña debe de ser mayor o igual a 6")
    private String passwordVerify;
}

