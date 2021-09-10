package api.carrito.compras.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Link {

    @JsonProperty
    private String rel;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HttpMethods type = HttpMethods.GET;

    @JsonProperty
    private String href;

    enum HttpMethods {
        GET,
        POST,
        PUT,
        DELETE
    }
}