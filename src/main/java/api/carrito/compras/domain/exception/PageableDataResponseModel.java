package api.carrito.compras.domain.exception;

import api.carrito.compras.domain.model.Link;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * PageableDataResponseModel class
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
public class PageableDataResponseModel {

    @JsonProperty
    private String type;

    @JsonProperty
    private String message;

    @JsonProperty
    private Object data;

    @JsonProperty
    private Long totalElements; // Total de todos los elementos de la consulta

    @JsonProperty
    private Integer totalPages; // Total de páginas (DE SIZE)

    @JsonProperty
    private Integer pageSize; // Número de elemntos de la pagina

    @JsonProperty
    private Integer currentPage;

    @JsonProperty
    private List<Link> links = new ArrayList<>();
}

