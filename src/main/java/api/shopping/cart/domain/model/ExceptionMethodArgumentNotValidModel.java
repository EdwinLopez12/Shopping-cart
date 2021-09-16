package api.shopping.cart.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ExceptionMethodArgumentNotValidModel class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

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
