package api.shopping.cart.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static api.shopping.cart.domain.utils.FormatDates.instantToString;

/**
 * GeneralResponseModel class
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
public class GeneralResponseModel {

    @JsonProperty
    @Builder.Default
    private String timestamp = instantToString(Instant.now());

    @JsonProperty
    private String status;

    @JsonProperty
    private ExceptionResponseModel exception;

    @JsonProperty
    private GeneralDataResponseModel response;
}
