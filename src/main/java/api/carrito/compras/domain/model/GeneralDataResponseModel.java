package api.carrito.compras.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralDataResponseModel {

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String type;

    @JsonProperty
    private String message;

    @JsonProperty
    private List<Object> data = new ArrayList<>();
}