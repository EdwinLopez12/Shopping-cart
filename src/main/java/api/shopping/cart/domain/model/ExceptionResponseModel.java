package api.shopping.cart.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * ExceptionResponseModel class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponseModel {

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String message;

    @JsonProperty
    private List<ExceptionMethodArgumentNotValidModel> errors = new ArrayList<>();

    @JsonProperty
    private String path;
}
