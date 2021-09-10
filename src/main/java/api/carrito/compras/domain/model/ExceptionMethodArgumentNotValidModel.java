package api.carrito.compras.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionMethodArgumentNotValidModel {

    @JsonProperty
    private String fieldName;

    @JsonProperty
    private Object rejectedValue;

    @JsonProperty
    private String errorMessage;

    @JsonProperty
    private String errorType;
}
