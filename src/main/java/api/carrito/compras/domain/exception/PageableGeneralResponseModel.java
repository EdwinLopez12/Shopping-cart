package api.carrito.compras.domain.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static api.carrito.compras.domain.utils.FormatDates.instantToString;

/**
 * PageableGeneralResponseModel class
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
public class PageableGeneralResponseModel {

    @JsonProperty
    @Builder.Default
    private String timestamp = instantToString(Instant.now());

    @JsonProperty
    @Builder.Default
    private String status = "Ok";

    @JsonProperty
    private PageableDataResponseModel response;

}

