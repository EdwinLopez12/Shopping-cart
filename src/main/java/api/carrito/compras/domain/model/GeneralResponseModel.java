package api.carrito.compras.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static api.carrito.compras.domain.utils.FormatDates.instantToString;

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
